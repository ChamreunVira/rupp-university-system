package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.dto.request.CreateScheduleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.StartSessionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.AttendanceSessionResponse;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScheduleResponse;

public interface AttendanceService {

    ScheduleResponse createSchedule(CreateScheduleRequest request);

    AttendanceSessionResponse startSession(StartSessionRequest request, String instructorId);

    void closeSession(Long sessionId, String instructorId);

}
