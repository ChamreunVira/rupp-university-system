package com.kh.rupp_dev.boukryuniversity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreResponse {
    private UUID id;

    private UUID studentId;
    private UUID subjectId;
    private UUID semesterId;
    private UUID userId;

    private BigDecimal score;
    private Integer version;
    private LocalDate creationAt;
    private LocalDate updatedAt;
    private boolean status;

}
