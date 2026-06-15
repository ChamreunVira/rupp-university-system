package com.kh.rupp_dev.boukryuniversity.service.impl;

import com.kh.rupp_dev.boukryuniversity.constant.AttendanceStatus;
import com.kh.rupp_dev.boukryuniversity.constant.SessionStatus;
import com.kh.rupp_dev.boukryuniversity.dto.request.CheckInRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.CreateScheduleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.StartSessionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.AttendanceSessionResponse;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScheduleResponse;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceRecord;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;
import com.kh.rupp_dev.boukryuniversity.entity.ClassSchedule;
import com.kh.rupp_dev.boukryuniversity.exception.ResourceNotFoundException;
import com.kh.rupp_dev.boukryuniversity.mapper.AttendaceMapper;
import com.kh.rupp_dev.boukryuniversity.repository.AttendanceRecordRepository;
import com.kh.rupp_dev.boukryuniversity.repository.AttendanceSessionRepository;
import com.kh.rupp_dev.boukryuniversity.repository.ClassScheduleRepository;
import com.kh.rupp_dev.boukryuniversity.service.AttendanceService;
import com.kh.rupp_dev.boukryuniversity.validation.DistanceValidation;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final ClassScheduleRepository scheduleRepository;
    private final AttendanceSessionRepository sessionRepository;
    private final AttendanceRecordRepository recordRepository;
    private final AttendaceMapper mapper;

    @Value("${attendance.jwt.secret}")
    private String jwtSecret;

    @Value("${attendance.jwt.expiry-minutes:10}")
    private Long sessionExpired;

    @Override
    public ScheduleResponse createSchedule(CreateScheduleRequest request) {

        ClassSchedule schedule = mapper.toSchedule(request);
        ClassSchedule saved = scheduleRepository.save(schedule);

        String allowedDayName = saved
            .getAllowedDay()
            .stream()
            .map(day -> day.name().substring(0, 3))
            .collect(Collectors.joining(", "));

        return mapper.toScheduleResponse(saved);
    }

    @Override
    public AttendanceSessionResponse startSession(StartSessionRequest request, String instructorId) {

        ClassSchedule schedule = scheduleRepository.findById(request.getScheduleId())
            .orElseThrow(() ->
                new IllegalArgumentException("Schedule not found: " + request.getScheduleId())
        );

        DayOfWeek today = LocalDateTime.now().getDayOfWeek();
        if (!schedule.getAllowedDay().contains(today)) {
            throw new IllegalStateException(
                "Connot start session on " +
                    today.name() +
                    "for this class. Allowed days: " +
                    schedule.getAllowedDay()
            );
        }

        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(schedule.getStartTime()) || now.isAfter(schedule.getEndTime())) {
            throw new IllegalStateException(
                "Cannot start session outside class hours. " +
                    "Class runs " +
                    schedule.getStartTime() +
                    " - " +
                    schedule.getEndTime()
            );
        }

        if (sessionRepository.existsByScheduleIdAndStatus(schedule.getId(), SessionStatus.ACTIVE)) {
            throw new IllegalStateException("An active session already exists for this class. ");
        }

        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(sessionExpired);
        String qrToken = generateSessionQrToken(schedule.getId(), expiresAt);

        AttendanceSession session = AttendanceSession.builder()
            .schedule(schedule)
            .qrToken(qrToken)
            .startTime(LocalDateTime.now())
            .endTime(expiresAt)
            .status(SessionStatus.ACTIVE)
            .instructor(instructorId)
            .build();

        AttendanceSession saved = sessionRepository.save(session);

        return AttendanceSessionResponse.builder()
            .sessionId(saved.getId())
            .qrToken(saved.getQrToken())
            .expiresAt(saved.getEndTime())
            .allowedDays(schedule.getAllowedDay())
            .todayAllowed(true)
            .status(saved.getStatus().name())
            .build();
    }

    @Override
    public AttendanceSessionResponse closeSession(Long sessionId, String instructorId) {

        AttendanceSession session = sessionRepository
            .findById(sessionId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Session not found :" + sessionId)
            );

        if (!session.getInstructor().equals(instructorId)) {
            throw new SecurityException(
                "You are not allowed authorized to close this session."
            );
        }

        session.setStatus(SessionStatus.CLOSED);
        sessionRepository.save(session);
        return null;
    }

    @Override
    public AttendanceRecord checkIn(CheckInRequest request) {

        AttendanceSession session = sessionRepository.findByQrToken(request.getQrToken())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Qr Token."));

        if (session.getStatus() != SessionStatus.ACTIVE) {
            throw new IllegalStateException("Session is expired or closed");
        }

        DayOfWeek today = LocalDateTime.now().getDayOfWeek();
        ClassSchedule schedule = session.getSchedule();
        if (schedule.getAllowedDay().contains(today)) {
            throw new IllegalStateException("Attendance not allowed on " + today.name() +
                    ". Allowed days: " + schedule.getAllowedDay());
        }

        double disstance = DistanceValidation.haversine(
                request.getLatitude(),
                request.getLongitude(),
                schedule.getLatitude(),
                schedule.getLongtitude()
        );

        if (disstance > schedule.getRadiusMeters()) {
            throw new IllegalStateException(
                    "You are too for from the classroom. " +
                            "Distance: " + Math.round(disstance) + "m, allowed: " + schedule.getRadiusMeters() + "m"
            );
        }

        if (recordRepository.existsByStudentIdAndSession(request.getStudentId(), session.getId())) {
            throw new IllegalStateException("You have already checked in for this session.");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lateThreshold = schedule.getStartTime()
                .plusMinutes(schedule.getCheckWindowMinute());
        AttendanceStatus status = now.isAfter(lateThreshold)
                ? AttendanceStatus.LATE
                : AttendanceStatus.PRESENT;

        AttendanceRecord record = AttendanceRecord.builder()
                .studentId(request.getStudentId())
                .session(session)
                .attendanceTime(LocalDateTime.now())
                .status(status)
                .build();

        return recordRepository.save(record);
    }

    @Override
    public List<ScheduleResponse> getAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(mapper::toScheduleResponse)
                .toList();
    }

    @Override
    public Optional<ScheduleResponse> getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .map(mapper::toScheduleResponse);
    }

    @Override
    public void deleteSchedule(Long scheduleId) {
        if (scheduleRepository.existsById(scheduleId)) {
            scheduleRepository.deleteById(scheduleId);
        }
    }

    @Override
    public ScheduleResponse updateSchedule(Long scheduleId, CreateScheduleRequest request) {
        ClassSchedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id: " + scheduleId));
        mapper.updateFromRequest(request, schedule);
        return mapper.toScheduleResponse(scheduleRepository.save(schedule));
    }

    private String generateSessionQrToken(
        Long schdeduleId,
        LocalDateTime expiresAt
    ) {
        return Jwts.builder()
            .claim("scheduleId", schdeduleId)
            .subject("Session")
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(
                Date.from(expiresAt.atZone(ZoneId.systemDefault()).toInstant())
            )
            .signWith(getSigningKey())
            .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

}
