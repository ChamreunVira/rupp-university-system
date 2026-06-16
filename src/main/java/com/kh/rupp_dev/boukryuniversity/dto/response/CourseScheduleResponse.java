package com.kh.rupp_dev.boukryuniversity.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseScheduleResponse {

    private Long scheduleId;

    private String dayOfWeek;

    private String startTime;

    private String endTime;

    private Integer room;

}
