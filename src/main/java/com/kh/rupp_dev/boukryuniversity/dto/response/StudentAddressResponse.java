package com.kh.rupp_dev.boukryuniversity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentAddressResponse {
    private Long id;
    private String houseNumber;
    private String street;
    private String sangkat;
    private String khan;
    private String province;
    private String country;
}
