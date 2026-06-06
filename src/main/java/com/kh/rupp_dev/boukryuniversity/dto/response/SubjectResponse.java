package com.kh.rupp_dev.boukryuniversity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectResponse {

    private Integer id;

    private String departmentId;

    private String departmentName;

    private String thumbnail;

    private String name;

    private String description;

    private String code;

}
