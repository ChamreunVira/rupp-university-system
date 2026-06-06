package com.kh.rupp_dev.boukryuniversity.service;

import java.util.List;

import com.kh.rupp_dev.boukryuniversity.dto.request.AssignPermissionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.request.RoleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.RoleResponse;

public interface RoleService {

	RoleResponse create(RoleRequest request);

	RoleResponse update(Integer uuid ,RoleRequest request);

	List<RoleResponse> findAll();

	RoleResponse findById(Integer uuid);

	void updateStatus(Integer uuid, String status);

	List<RoleResponse> findByActive(String status);

	RoleResponse addPermission(Integer roleId , AssignPermissionRequest request);

	RoleResponse setPermission(Integer roleId , AssignPermissionRequest request);

	void deletePermission(Integer roleId , Integer permissionId);

}
