package com.kh.rupp_dev.boukryuniversity.dto.request;


public record CheckInRequest(
        Long classId,
        Long studentId,
        String token
) {}
