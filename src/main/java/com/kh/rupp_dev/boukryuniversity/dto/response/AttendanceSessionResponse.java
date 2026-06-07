package com.kh.rupp_dev.boukryuniversity.dto.response;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttendanceSessionResponse {
    
    private Long sessionId;
    private String qrToken;
    private LocalDateTime expiresAt;
    private List<DayOfWeek> allowedDays;
    private boolean todayAllowed;
    private String status;
    
}
