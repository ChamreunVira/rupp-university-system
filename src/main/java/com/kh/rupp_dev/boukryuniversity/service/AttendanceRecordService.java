package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.entity.AttendanceRecord;

import java.util.List;
import java.util.UUID;

public interface AttendanceRecordService {

    AttendanceRecord markAllAttendace(UUID studentId, String qrToken);

    List<AttendanceRecord> getStudentAttendanc(UUID studentId);

    List<AttendanceRecord> getSessionAttendance(UUID sessionId);

}
