package com.kh.rupp_dev.boukryuniversity.controller;

import com.kh.rupp_dev.boukryuniversity.dto.request.CreateScheduleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.StartSessionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.AttendanceSessionResponse;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScheduleResponse;
import com.kh.rupp_dev.boukryuniversity.service.AttendanceService;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> createSchedule(
        @RequestBody CreateScheduleRequest request
    ) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                service.createSchedule(request)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", e.getMessage())
            );
        }
    }

    @PostMapping("/session/start")
    public ResponseEntity<AttendanceSessionResponse> startSession(
        @RequestBody StartSessionRequest request,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        String instructor = userDetails.getUsername();
        return ResponseEntity.ok(service.startSession(request, instructor));
    }

/*
    @PatchMapping("/session/close")
    public ResponseEntity<AttendanceSessionResponse> closeSession(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        service.closeSession(id, userDetails.getUsername());
        return ResponseEntity.ok().body(Map.of("message", "Session closed successfully."));
    }
*/

    @GetMapping("/{scheduleId}")
    public ResponseEntity<Optional<ScheduleResponse>> getById(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(service.getScheduleById(scheduleId));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> update(@PathVariable Long scheduleId, @RequestBody CreateScheduleRequest request) {
        return ResponseEntity.ok(service.updateSchedule(scheduleId, request));
    }
}
