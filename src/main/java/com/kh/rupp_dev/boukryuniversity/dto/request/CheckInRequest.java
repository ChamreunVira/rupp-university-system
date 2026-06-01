package com.kh.rupp_dev.boukryuniversity.dto.request;

import java.util.UUID;

public record CheckInRequest(
        UUID studentId,
        String token
) {}
