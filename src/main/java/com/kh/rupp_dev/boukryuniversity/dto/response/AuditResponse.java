package com.kh.rupp_dev.boukryuniversity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditResponse {

    private Long id;

    private String username;

    private String action;

    private String entityName;

    private String entityId;

    private String oldValue;

    private String newValue;

    private LocalDateTime timestamp;

}
