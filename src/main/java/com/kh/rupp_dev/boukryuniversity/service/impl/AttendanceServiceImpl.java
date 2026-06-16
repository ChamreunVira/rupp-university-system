package com.kh.rupp_dev.boukryuniversity.service.impl;

import com.kh.rupp_dev.boukryuniversity.constant.SessionStatus;
import com.kh.rupp_dev.boukryuniversity.dto.request.CreateScheduleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.StartSessionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.AttendanceSessionResponse;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScheduleResponse;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;
import com.kh.rupp_dev.boukryuniversity.entity.ClassSchedule;
import com.kh.rupp_dev.boukryuniversity.exception.ResourceNotFoundException;
import com.kh.rupp_dev.boukryuniversity.mapper.AttendaceMapper;
import com.kh.rupp_dev.boukryuniversity.repository.AttendanceSessionRepository;
import com.kh.rupp_dev.boukryuniversity.repository.ClassScheduleRepository;
import com.kh.rupp_dev.boukryuniversity.service.AttendanceService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final ClassScheduleRepository scheduleRepository;
    private final AttendanceSessionRepository sessionRepository;
    private final AttendaceMapper mapper;

    @Value("${attendance.jwt.secret}")
    private String jwtSecret;

    @Value("${attendance.jwt.expiry-minutes:10}")
    private int sessionExpired;

    @Override
    public ScheduleResponse createSchedule(CreateScheduleRequest request) {
        ClassSchedule schedule = mapper.toSchedule(request);

        ClassSchedule saved = scheduleRepository.save(schedule);

        String allowedDayName = saved.getAllowedDay().stream()
                .map(day -> day.name().substring(0, 3))
                .collect(Collectors.joining(", "));

        return mapper.toScheduleResponse(saved);
    }

    @Override
    public AttendanceSessionResponse startSession(StartSessionRequest request, String instructorId) {

        ClassSchedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found: " + request.getScheduleId()));

        DayOfWeek today = LocalDateTime.now().getDayOfWeek();
        if (!schedule.getAllowedDay().contains(today)){
            throw new IllegalStateException(
                    "Connot start session on " + today.name() +
                            "for this class. Allowed days: " + schedule.getAllowedDay()
            );
        }

        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(schedule.getStartTime()) || now.isAfter(schedule.getEndTime())) {
            throw new IllegalStateException(
                    "Cannot start session outside class hours. " +
                            "Class runs " + schedule.getStartTime() + " - " + schedule.getEndTime()
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
    public void closeSession(Long sessionId, String instructorId) {
        AttendanceSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found :" + sessionId ));

        if (!session.getInstructor().equals(instructorId)) {
            throw new SecurityException("You are not allowed authorized to close this session.");
        }

        session.setStatus(SessionStatus.CLOSED);
        sessionRepository.save(session);
    }

    private String generateSessionQrToken(Long schdeduleId, LocalDateTime expiresAt) {

        byte[] keyByte = jwtSecret.getBytes();
        var key = Keys.hmacShaKeyFor(keyByte);

        return Jwts.builder()
                .subject("session")
                .claim("scheduleId", schdeduleId)
                .issuedAt(new Date())
                .expiration(Timestamp.valueOf(expiresAt))
                .signWith(key)
                .compact();
    }

}
