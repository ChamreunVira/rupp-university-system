package com.kh.rupp_dev.boukryuniversity.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.rupp_dev.boukryuniversity.dto.response.AuditLogResponse;
import com.kh.rupp_dev.boukryuniversity.entity.AuditLog;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuditLogMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuditLogResponse toResponse(AuditLog auditLog) {
        if (auditLog == null) {
            return null;
        }

        return AuditLogResponse.builder()
                .id(auditLog.getId())
                .action(auditLog.getAction())
                .changeAt(auditLog.getChangeAt())
                .tableName(auditLog.getTableName())
                .recordId(auditLog.getRecordId() != null ? auditLog.getRecordId().toString() : null)
                .oldData(jsonToMap(auditLog.getOldData()))
                .newData(jsonToMap(auditLog.getNewData()))
                .build();
    }

    private Map<String, Object> jsonToMap(String json) {
        try {
            if (json == null) {
                return null;
            }
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }
}
