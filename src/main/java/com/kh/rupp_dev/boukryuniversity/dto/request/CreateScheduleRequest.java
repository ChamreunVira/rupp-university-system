package com.kh.rupp_dev.boukryuniversity.dto.request;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateScheduleRequest {

    private UUID courseId;
    private String coursName;
    private List<DayOfWeek> allowedDays;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer checkInWindowMinutes;
    private Double latitude;
    private Double longitude;
    private Double radiusMeters;

}
