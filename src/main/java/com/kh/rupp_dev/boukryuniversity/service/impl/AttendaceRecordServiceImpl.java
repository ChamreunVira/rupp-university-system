package com.kh.rupp_dev.boukryuniversity.service.impl;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public void checkIn(CheckInRequest request) {

        AttendanceSession session = sessionRepository.findByQrToken(request.token())
            .orElseThrow(() -> new ResourceNotFoundException("Session not found"));

        if (session.getStatus() != SessionStatus.ACTIVE) {
            throw new RuntimeException("Session closed");
        }

        if (session.getEndTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("QR expired");
        }

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
    public AttendanceRecord markAllAttendace(Integer studentId, String qrToken) {

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
    public List<AttendanceRecord> getStudentAttendanc(Integer studentId) {
        return recordRepository.findByStudentId(studentId);
    }

   @Override
    public List<AttendanceRecord> getSessionAttendance(Integer sessionId) {
        return recordRepository.findBySessionId(sessionId);
    }

}
