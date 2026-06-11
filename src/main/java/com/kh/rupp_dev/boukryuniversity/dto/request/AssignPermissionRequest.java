package com.kh.rupp_dev.boukryuniversity.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssignPermissionRequest {

    @NotNull(message = "PermissionId is required.")
    private List<UUID> permissionIds;
}
