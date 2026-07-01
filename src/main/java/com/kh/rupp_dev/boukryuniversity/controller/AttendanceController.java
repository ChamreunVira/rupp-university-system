package com.kh.rupp_dev.boukryuniversity.controller;

import com.kh.rupp_dev.boukryuniversity.dto.request.CheckInRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.CreateScheduleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.StartSessionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.AttendanceSessionResponse;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScheduleResponse;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceRecord;
import com.kh.rupp_dev.boukryuniversity.service.AttendanceService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService service;

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createSchedule(request));
    }

    @PostMapping("/session/start")
    public ResponseEntity<AttendanceSessionResponse> startSession(
        @RequestBody StartSessionRequest request,
        @AuthenticationPrincipal UserDetails userDetails
    ){
        String instructor = userDetails.getUsername();
        return ResponseEntity.ok(service.startSession(request, instructor));
    }

    @PatchMapping("/session/close/{scheduleId}")
    public ResponseEntity<AttendanceSessionResponse> closeSession(
            @PathVariable Long scheduleId,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        AttendanceSessionResponse response = service.closeSession(scheduleId, userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/check-in")
    public ResponseEntity<AttendanceRecord> checkIn(@RequestBody CheckInRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.checkIn(request));
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<Optional<ScheduleResponse>> getById(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(service.getScheduleById(scheduleId));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/schedule/{scheduleId}")
    public ResponseEntity<ScheduleResponse> update(@PathVariable Long scheduleId, @RequestBody CreateScheduleRequest request) {
        return ResponseEntity.ok(service.updateSchedule(scheduleId, request));
    }

}
