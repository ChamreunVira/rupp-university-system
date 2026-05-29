package com.kh.rupp_dev.boukryuniversity.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotpRequest {
    @Min(100000)
    @Max(999999)
    private int code;
}
