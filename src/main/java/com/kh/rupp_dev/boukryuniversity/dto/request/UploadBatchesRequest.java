package com.kh.rupp_dev.boukryuniversity.dto.request;

import com.kh.rupp_dev.boukryuniversity.constant.UploadBatchesStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadBatchesRequest {

    private Long userId;

    private String fileName;

    private UploadBatchesStatus status;

    private Integer totalRow;

    private Integer successRow;

    private Integer failRow;

}
