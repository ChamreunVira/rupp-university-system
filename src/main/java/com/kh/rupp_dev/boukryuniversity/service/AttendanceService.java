package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.dto.request.CheckInRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.CreateScheduleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.StartSessionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.AttendanceSessionResponse;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScheduleResponse;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceRecord;

public interface AttendanceService {

    ScheduleResponse createSchedule(CreateScheduleRequest request);

    AttendanceSessionResponse startSession(
        StartSessionRequest request,
        String instructorId
    );

    void closeSession(Long sessionId, String instructorId);

    AttendanceRecord checkIn(CheckInRequest request);

}
