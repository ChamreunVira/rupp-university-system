package com.kh.rupp_dev.boukryuniversity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckInRequest {
    private String qrToken;
    private UUID studentId;
    private Double latitude;
    private Double longitude;
}
