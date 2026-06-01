package com.kh.rupp_dev.boukryuniversity.repository;

import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Long> {
    Optional<AttendanceSession> findByQrToken(String qrToken);
}
