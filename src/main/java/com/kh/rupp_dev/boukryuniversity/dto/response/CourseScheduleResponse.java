package com.kh.rupp_dev.boukryuniversity.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseScheduleResponse {

    private UUID scheduleId;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private Integer room;
}
