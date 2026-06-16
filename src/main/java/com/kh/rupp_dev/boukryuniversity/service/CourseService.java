package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.dto.request.CourseRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.CourseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CourseService {

    CourseResponse create(CourseRequest request);

    CourseResponse update(Long semesterId, Long subjectId, CourseRequest request);

    void delete(Long semesterId, Long subjectId);

    Page<CourseResponse> getAll(Pageable pageable);

    CourseResponse getById(Long semesterId, Long subjectId);
}
