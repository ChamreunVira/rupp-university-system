package com.kh.rupp_dev.boukryuniversity.service;

import java.util.List;

import com.kh.rupp_dev.boukryuniversity.dto.request.AssignPermissionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.RoleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.RoleResponse;

public interface RoleService {

	RoleResponse create(RoleRequest request);

	RoleResponse update(Long id ,RoleRequest request);

	List<RoleResponse> findAll();

	RoleResponse findById(Long id);

	void updateStatus(Long id, String status);

	List<RoleResponse> findByActive(String status);

	RoleResponse addPermission(Long roleId , AssignPermissionRequest request);

	RoleResponse setPermission(Long roleId , AssignPermissionRequest request);

	void deletePermission(Long roleId , Long permissionId);

}
