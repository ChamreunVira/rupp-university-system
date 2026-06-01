package com.kh.rupp_dev.boukryuniversity.service.impl;

import com.kh.rupp_dev.boukryuniversity.constant.AttendanceStatus;
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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttendaceRecordServiceImpl implements AttendanceRecordService {

    private final AttendanceSessionRepository sessionRepository;
    private final AttendanceRecordRepository recordRepository;
    private final StudentRepository studentRepository;

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
        return List.of();
    }

   @Override
    public List<AttendanceRecord> getSessionAttendance(UUID sessionId) {
        return List.of();
    }

}
