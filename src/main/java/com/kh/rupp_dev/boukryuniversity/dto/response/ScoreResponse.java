package com.kh.rupp_dev.boukryuniversity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScoreResponse {
    private Long id;

    private Long studentId;
    private Long subjectId;
    private Long semesterId;
    private Long userId;

    private BigDecimal score;
    private Integer version;
    private LocalDate creationAt;
    private LocalDate updatedAt;
    private boolean status;

}
