package com.kh.rupp_dev.boukryuniversity.service.impl;

import com.kh.rupp_dev.boukryuniversity.constant.SessionStatus;
import com.kh.rupp_dev.boukryuniversity.entity.AttendanceSession;
import com.kh.rupp_dev.boukryuniversity.entity.Class;
import com.kh.rupp_dev.boukryuniversity.exception.ResourceNotFoundException;
import com.kh.rupp_dev.boukryuniversity.repository.AttendanceSessionRepository;
import com.kh.rupp_dev.boukryuniversity.repository.ClassRepository;
import com.kh.rupp_dev.boukryuniversity.service.AttendaceSessionService;
import com.kh.rupp_dev.boukryuniversity.service.QrService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AttendanceSessionServiceImpl implements AttendaceSessionService {

    private final AttendanceSessionRepository sessionRepository;
    private final ClassRepository classRepository;
    private final QrService qrService;

    @Override
    public AttendanceSession createSession(Integer classId) {

        Class clazz = classRepository.findById(classId)
            .orElseThrow(() -> new ResourceNotFoundException("Class not found"));

        AttendanceSession session = AttendanceSession.builder()
                .clazz(clazz)
                .qrToken(qrService.generateToken())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(15))
                .status(SessionStatus.ACTIVE)
                .build();

        return sessionRepository.save(session);
    }

    @Override
    public AttendanceSession findById(Integer sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found"));
    }

    @Override
    public void closeSession(Integer sessionId) {

        AttendanceSession session = sessionRepository.findById(sessionId)
                .orElseThrow();

        session.setStatus(SessionStatus.CLOSED);
        sessionRepository.save(session);
    }

}
