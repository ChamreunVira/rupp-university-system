package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.RoleRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.RoleResponse;
import com.kh.rupp_dev.boukryuniversity.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Role toEntity(RoleRequest request) {
        if (request == null) {
            return null;
        }

        Role role = new Role();
        role.setDescription(request.getDescription());
        role.setStatus(request.getStatus());
        return role;
    }

    public RoleResponse toResponse(Role role) {
        if (role == null) {
            return null;
        }

        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .status(role.getStatus())
                .build();
    }

    public void updateFromRequest(Role role, RoleRequest request) {
        if (request == null) {
            return;
        }

        if (request.getName() != null) {
            role.setName(request.getName());
        }
        if (request.getDescription() != null) {
            role.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            role.setStatus(request.getStatus());
        }
    }
}
