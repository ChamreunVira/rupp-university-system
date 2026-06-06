package com.kh.rupp_dev.boukryuniversity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentsFilterResponse {

    private Integer studentId;

    private String studentCode;

    private String enFirstName;

    private String enLastName;

    private String khFirstName;

    private String khLastName;

    private String gender;

    private Integer classId;

    private String className;

    private Integer departmentId;
}
