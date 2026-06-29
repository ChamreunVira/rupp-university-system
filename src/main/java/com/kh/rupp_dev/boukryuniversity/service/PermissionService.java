package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.dto.request.PermissionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {

    PermissionResponse create(PermissionRequest request);

    PermissionResponse update(Long id , PermissionRequest request);

    void delete(Long id);

    List<PermissionResponse> getAll();

    PermissionResponse getById(Long id);

    List<PermissionResponse> findByModule(String module);
}