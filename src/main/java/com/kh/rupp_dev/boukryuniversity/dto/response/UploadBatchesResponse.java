package com.kh.rupp_dev.boukryuniversity.dto.response;

import com.kh.rupp_dev.boukryuniversity.constant.UploadBatchesStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadBatchesResponse {

    private Integer id;

    private Integer userId;

    private String username;

    private String fileName;

    private UploadBatchesStatus status;

    private Integer totalRow;

    private Integer successRow;

    private Integer failRow;

    private LocalDateTime createAt;

    private LocalDateTime completedAt;
}
