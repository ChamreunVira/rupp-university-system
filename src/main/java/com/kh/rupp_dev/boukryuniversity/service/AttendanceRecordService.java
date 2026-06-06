package com.kh.rupp_dev.boukryuniversity.service;

import java.util.List;

import com.kh.rupp_dev.boukryuniversity.dto.request.CheckInRequest;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceRecord;

public interface AttendanceRecordService {

    void checkIn(CheckInRequest request);

    AttendanceRecord markAllAttendace(Integer studentId, String qrToken);

    List<AttendanceRecord> getStudentAttendanc(Integer studentId);

    List<AttendanceRecord> getSessionAttendance(Integer sessionId);

}
