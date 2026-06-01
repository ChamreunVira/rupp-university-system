package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;

import java.util.UUID;

public interface AttendaceSessionService {

   AttendanceSession createSession(UUID classId);

   AttendanceSession findById(Long sessionId);

   void closeSession(Long sessionId);

}
