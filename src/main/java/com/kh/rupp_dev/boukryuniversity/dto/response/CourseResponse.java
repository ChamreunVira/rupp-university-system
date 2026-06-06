package com.kh.rupp_dev.boukryuniversity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {

    private Integer subjectId;

    private String subjectName;

    private Integer semesterId;
    private String semesterName;

    private Integer instructorId;
    private String instructorName;

    private String name;

    private String description;

    private String schedule;

    private List<CourseScheduleResponse> schedules;

    private String startAt;

    private String endAt;
}
