package com.kh.rupp_dev.boukryuniversity.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.rupp_dev.boukryuniversity.dto.request.CreateSessionRequest;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;
import com.kh.rupp_dev.boukryuniversity.service.AttendaceSessionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class AttendanceSessionController {
    
    private final AttendaceSessionService service;
 
    @PostMapping
    public AttendanceSession create(@RequestBody CreateSessionRequest request) {
        return service.createSession(request);
    }

    @PatchMapping("/{id}/close")
    public void close(@PathVariable Long id) {
        service.closeSession(id);
    }
}
