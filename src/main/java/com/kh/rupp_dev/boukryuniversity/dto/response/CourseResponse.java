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

    private Long subjectId;

    private String subjectName;

    private Long semesterId;
    private String semesterName;

    private Long instructorId;
    private String instructorName;

    private String name;

    private String description;

    private String schedule;

    private List<CourseScheduleResponse> schedules;

    private String startAt;

    private String endAt;
}
