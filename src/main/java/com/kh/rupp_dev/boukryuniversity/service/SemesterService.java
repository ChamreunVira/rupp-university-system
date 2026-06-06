package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.dto.request.SemesterRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.SemesterResponse;

import java.util.List;

public interface SemesterService {

    SemesterResponse create(SemesterRequest request);

    SemesterResponse update(Integer id , SemesterRequest request);

    void delete(Integer id);

    List<SemesterResponse> getAll();

    SemesterResponse getById(Integer id);

}
