package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.dto.request.DepartmentRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse create(DepartmentRequest request);

    List<DepartmentResponse> getAll();

    DepartmentResponse getById(Long id);

    DepartmentResponse update(Long id, DepartmentRequest request);

    void delete(Long id);

}

