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
                .startTime(schedule.getStartTime())
                .closeTime(schedule.getEndTime())
                .build();
    }

    public void updateFromRequest(CreateScheduleRequest request, ClassSchedule schedule) {
        if (request==null || schedule==null) return;
        if (request.getCourseId() != null) {schedule.setCourseId(request.getCourseId());}
        if (request.getCoursName() != null) {schedule.setCourseName(request.getCoursName());}
        if (request.getAllowedDays() != null) {schedule.setAllowedDay(request.getAllowedDays());}
        if (request.getStartTime() != null) {schedule.setStartTime(request.getStartTime());}
        if (request.getEndTime() != null) {schedule.setEndTime(request.getEndTime());}
        if (request.getLatitude() != null) {schedule.setLatitude(request.getLatitude());}
        if (request.getLongitude() != null) {schedule.setLongtitude(request.getLongitude());}
        if (request.getRadiusMeters() != null) {schedule.setRadiusMeters(request.getRadiusMeters());}
    }
}
