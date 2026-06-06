package com.kh.rupp_dev.boukryuniversity.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.kh.rupp_dev.boukryuniversity.entity.ClassSchedule;
import com.kh.rupp_dev.boukryuniversity.exception.SessionNotFoundException;
import com.kh.rupp_dev.boukryuniversity.repository.ClassScheduleRepository;
import org.springframework.stereotype.Service;

import com.kh.rupp_dev.boukryuniversity.constant.AttendanceStatus;
import com.kh.rupp_dev.boukryuniversity.constant.SessionStatus;
import com.kh.rupp_dev.boukryuniversity.dto.request.CheckInRequest;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceRecord;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;
import com.kh.rupp_dev.boukryuniversity.entity.Student;
import com.kh.rupp_dev.boukryuniversity.exception.DuplicateResourceException;
import com.kh.rupp_dev.boukryuniversity.exception.ResourceNotFoundException;
import com.kh.rupp_dev.boukryuniversity.repository.AttendanceRecordRepository;
import com.kh.rupp_dev.boukryuniversity.repository.AttendanceSessionRepository;
import com.kh.rupp_dev.boukryuniversity.repository.StudentRepository;
import com.kh.rupp_dev.boukryuniversity.service.AttendanceRecordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendaceRecordServiceImpl implements AttendanceRecordService {

    private final AttendanceSessionRepository sessionRepository;
    private final AttendanceRecordRepository recordRepository;
    private final StudentRepository studentRepository;
    private final ClassScheduleRepository scheduleRepository;

    @Override
    public void checkIn(CheckInRequest request) {

        AttendanceSession session = sessionRepository.findByQrToken(request.token())
            .orElseThrow(() -> new SessionNotFoundException("Session not found"));

        if (session.getStatus() != SessionStatus.ACTIVE) {
            throw new SessionNotFoundException("Session closed");
        }

        if (session.getEndTime().isBefore(LocalDateTime.now())) {
            throw new SessionNotFoundException("QR expired");
        }

        DayOfWeek today = LocalDateTime.now().getDayOfWeek();
        ClassSchedule schedule = scheduleRepository.findByClassId(request.classId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with: " + request.classId()));

        Student student = studentRepository.findById(request.studentId())
            .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        boolean exists = recordRepository.existsByStudentAndSession(
                student,
                session
        );

        if (exists) {
            throw new DuplicateResourceException("Atttendance already marked");
        }

        AttendanceRecord record = AttendanceRecord.builder()
                                    .student(null)
                                    .session(session)
                                    .attendanceTime(LocalDateTime.now())
                                    .status(AttendanceStatus.PRESENT)
                                    .build();

        recordRepository.save(record);

    }

    @Override
    public AttendanceRecord markAllAttendace(UUID studentId, String qrToken) {

        AttendanceSession session = sessionRepository.findByQrToken(qrToken)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        boolean exists = recordRepository.existsByStudentAndSession(student, session);

        if (exists) {
            throw new DuplicateResourceException("Attendance already marked");
        }

        AttendanceRecord record = AttendanceRecord.builder()
                .student(student)
                .session(session)
                .attendanceTime(LocalDateTime.now())
                .status(AttendanceStatus.PRESENT)
                .build();

        return recordRepository.save(record);
    }

    @Override
    public List<AttendanceRecord> getStudentAttendanc(UUID studentId) {
        return recordRepository.findByStudentId(studentId);
    }

   @Override
    public List<AttendanceRecord> getSessionAttendance(UUID sessionId) {
        return recordRepository.findBySessionId(sessionId);
    }

}
