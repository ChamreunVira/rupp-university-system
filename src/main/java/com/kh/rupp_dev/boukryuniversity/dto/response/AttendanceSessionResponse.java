package com.kh.rupp_dev.boukryuniversity.dto.response;

import java.time.LocalDateTime;

public record AttendanceSessionResponse(
        Integer sessionId,
        String qrToken,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}
