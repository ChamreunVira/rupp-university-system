package com.kh.rupp_dev.boukryuniversity.dto.request;

import java.util.UUID;

public record CheckInRequest(
        UUID classId,
        UUID studentId,
        String token,
        Double latitute,
        Double longtitute
) {}
