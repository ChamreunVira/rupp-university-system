package com.kh.rupp_dev.boukryuniversity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsFilterRequest {
    private Long classId;
//    private Long departmentId;
}
