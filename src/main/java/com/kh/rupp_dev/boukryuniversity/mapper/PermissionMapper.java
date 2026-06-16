package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.PermissionRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.PermissionResponse;
import com.kh.rupp_dev.boukryuniversity.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public Permission toEntity(PermissionRequest request) {
        if (request == null) {
            return null;
        }

        Permission permission = new Permission();
        permission.setName(request.getName());
        permission.setDescription(request.getDescription());
        permission.setModule(request.getModule());
        return permission;
    }

    public PermissionResponse toResponse(Permission permission) {
        if (permission == null) {
            return null;
        }

        return PermissionResponse.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .module(permission.getModule())
                .status(permission.isStatus())
                .build();
    }

    public void updateFromRequest(Permission permission, PermissionRequest request) {
        if (request == null) {
            return;
        }

        if (request.getName() != null) {
            permission.setName(request.getName());
        }
        if (request.getDescription() != null) {
            permission.setDescription(request.getDescription());
        }
        if (request.getModule() != null) {
            permission.setModule(request.getModule());
        }
    }
}
