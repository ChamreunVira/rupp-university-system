package com.kh.rupp_dev.boukryuniversity.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AssignPermissionRequest {

    @NotNull(message = "PermissionId is required.")
    private List<Long> permissionIds;

}
