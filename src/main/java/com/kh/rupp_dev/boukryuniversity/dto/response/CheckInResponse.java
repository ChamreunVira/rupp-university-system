package com.kh.rupp_dev.boukryuniversity.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckInResponse {
    private Long recordId;
    private UUID studentId;
    private UUID sessionId;
    private String status;
    private LocalDateTime attendanceTime;
    private String message;
}
