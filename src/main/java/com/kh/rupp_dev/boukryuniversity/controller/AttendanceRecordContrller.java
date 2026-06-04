package com.kh.rupp_dev.boukryuniversity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.rupp_dev.boukryuniversity.dto.request.CheckInRequest;
import com.kh.rupp_dev.boukryuniversity.service.AttendanceRecordService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceRecordContrller {
    
    private final AttendanceRecordService service;

    @PostMapping("/check-in")
    public void chcekIn( @RequestBody CheckInRequest request) {
        service.checkIn(request);
    }

}
