package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.CreateScheduleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScheduleResponse;
import com.kh.rupp_dev.boukryuniversity.entity.ClassSchedule;
import org.springframework.stereotype.Component;

@Component
public class AttendaceMapper {

    public ClassSchedule toSchedule(CreateScheduleRequest request) {
        if (request == null) return null;
        return ClassSchedule.builder()
                .courseId(request.getCourseId())
                .courseName(request.getCoursName())
                .allowedDay(request.getAllowedDays())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .checkWindowMinute(
                        request.getCheckInWindowMinutes() != null ? request.getCheckInWindowMinutes() : 10
                )
                .latitude(request.getLatitude())
                .longtitude(request.getLongitude())
                .radiusMeters(request.getRadiusMeters())
                .build();
    }

    public ScheduleResponse toScheduleResponse(ClassSchedule schedule) {
        if (schedule == null) return null;
        return ScheduleResponse.builder()
                .scheduleId(schedule.getId())
                .courseName(schedule.getCourseName())
                .allowedDay(schedule.getAllowedDay())
                .build();
    }
}
