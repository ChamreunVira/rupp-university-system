package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.dto.request.CourseRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.CourseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CourseService {

    CourseResponse create(CourseRequest request);

    CourseResponse update(Integer semesterId, Integer subjectId, CourseRequest request);

    void delete(Integer semesterId, Integer subjectId);

    Page<CourseResponse> getAll(Pageable pageable);

    CourseResponse getById(Integer semesterId, Integer subjectId);
}
