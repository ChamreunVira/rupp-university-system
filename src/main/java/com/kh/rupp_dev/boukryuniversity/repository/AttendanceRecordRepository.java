package com.kh.rupp_dev.boukryuniversity.repository;

import com.kh.rupp_dev.boukryuniversity.entity.AttendanceRecord;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;
import com.kh.rupp_dev.boukryuniversity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    boolean existsByStudentAndSession(Student student, AttendanceSession session);
}
