package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.dto.request.CheckInRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.CreateScheduleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.StartSessionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.AttendanceSessionResponse;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScheduleResponse;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceRecord;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    ScheduleResponse createSchedule(CreateScheduleRequest request);

    AttendanceSessionResponse startSession(StartSessionRequest request, String instructorId);

    AttendanceSessionResponse closeSession(Long sessionId, String instructorId);

    AttendanceRecord checkIn(CheckInRequest request);

    List<ScheduleResponse> getAll();
    
    Optional<ScheduleResponse> getScheduleById(Long scheduleId);
    
    void deleteSchedule(Long scheduleId);
    
    ScheduleResponse updateSchedule(Long scheduleId, CreateScheduleRequest request);
}
