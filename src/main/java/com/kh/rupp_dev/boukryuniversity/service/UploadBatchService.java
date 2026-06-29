package com.kh.rupp_dev.boukryuniversity.service;

import com.kh.rupp_dev.boukryuniversity.constant.UploadBatchesStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadBatchService {

    private Long id;

    private String fileName;

    private UploadBatchesStatus status;

    private Integer totalRow;

    private Integer successRow;

    private Integer failRow;

    private LocalDateTime createAt;

    private LocalDateTime completedAt;

}
