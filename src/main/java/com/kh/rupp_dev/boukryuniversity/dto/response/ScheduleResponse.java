package com.kh.rupp_dev.boukryuniversity.dto.response;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponse {
    private Long scheduleId;
    private String courseName;
    private List<DayOfWeek> allowedDay;
    private LocalDateTime startTime;
    private LocalDateTime closeTime;
}
