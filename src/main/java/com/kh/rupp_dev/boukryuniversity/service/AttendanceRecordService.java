package com.kh.rupp_dev.boukryuniversity.service;

import java.util.List;
import java.util.UUID;

import com.kh.rupp_dev.boukryuniversity.dto.request.CheckInRequest;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceRecord;

public interface AttendanceRecordService {

    void checkIn(CheckInRequest request);

    AttendanceRecord markAllAttendace(UUID studentId, String qrToken);

    List<AttendanceRecord> getStudentAttendanc(UUID studentId);

    List<AttendanceRecord> getSessionAttendance(UUID sessionId);

}
