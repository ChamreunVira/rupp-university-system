package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.dto.request.ScoreRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScoreResponse;

import java.util.List;

public interface ScoreService {

    ScoreResponse create(ScoreRequest request);

    ScoreResponse update(Integer id, ScoreRequest request);

    ScoreResponse getById(Integer id);

    List<ScoreResponse> getAll();

    List<ScoreResponse> findByStudentId(Integer studentId);

    List<ScoreResponse> findByCourse(Integer semesterId, Integer subjectId);

    void delete(Integer id);

}
