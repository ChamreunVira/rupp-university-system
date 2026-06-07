package com.kh.rupp_dev.boukryuniversity.repository;

import com.kh.rupp_dev.boukryuniversity.constant.SessionStatus;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Long> {

    Optional<AttendanceSession> findByQrToken(String qrToken);

    List<AttendanceSession> findByScheduleIdAndStatus(Long scheduleId, SessionStatus status);

    boolean existsByScheduleIdAndStatus(Long scheduleId, SessionStatus status);

}
