package com.kh.rupp_dev.boukryuniversity.controller;

import org.springframework.web.bind.annotation.*;
import com.kh.rupp_dev.boukryuniversity.dto.request.CreateSessionRequest;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;
import com.kh.rupp_dev.boukryuniversity.service.AttendaceSessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class AttendanceSessionController {
    
    private final AttendaceSessionService service;
 
    @PostMapping
    public AttendanceSession create(@RequestBody CreateSessionRequest request) {
        return service.createSession(request.classId());
    }

    @PatchMapping("/{id}/close")
    public void close(@PathVariable Long id) {
        service.closeSession(id);
    }

}
