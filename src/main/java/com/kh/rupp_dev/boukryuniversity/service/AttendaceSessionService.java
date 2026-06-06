package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;


public interface AttendaceSessionService {

   AttendanceSession createSession(Integer classId);

   AttendanceSession findById(Integer sessionId);

   void closeSession(Integer sessionId);

}
