package com.kh.rupp_dev.boukryuniversity.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.rupp_dev.boukryuniversity.entity.AttendanceRecord;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;
import com.kh.rupp_dev.boukryuniversity.entity.Student;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    boolean existsByStudentAndSession(Student student, AttendanceSession session);

    List<AttendanceRecord> findBySessionId(Long sessionId);

    List<AttendanceRecord> findByStudentId(UUID studentId);

    List<AttendanceRecord> findBySessionId(UUID sessionId);

}
