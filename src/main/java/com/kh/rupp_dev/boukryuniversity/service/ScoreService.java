package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.dto.request.ScoreRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScoreResponse;

import java.util.List;

public interface ScoreService {

    ScoreResponse create(ScoreRequest request);

    ScoreResponse update(Long id, ScoreRequest request);

    ScoreResponse getById(Long id);

    List<ScoreResponse> getAll();

    List<ScoreResponse> findByStudentId(Long studentId);

    List<ScoreResponse> findByCourse(Long semesterId, Long subjectId);

    void delete(Long id);

}
