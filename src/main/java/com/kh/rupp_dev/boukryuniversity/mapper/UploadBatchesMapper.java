package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.UploadBatchesRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.UploadBatchesResponse;
import com.kh.rupp_dev.boukryuniversity.entity.UploadBatches;
import org.springframework.stereotype.Component;

@Component
public class UploadBatchesMapper {

    public UploadBatches toEntity(UploadBatchesRequest request) {
        if (request == null) {
            return null;
        }

        return UploadBatches.builder()
                .fileName(request.getFileName())
                .status(request.getStatus())
                .totalRow(request.getTotalRow())
                .successRow(request.getSuccessRow())
                .failRow(request.getFailRow())
                .build();
    }

    public UploadBatchesResponse toResponse(UploadBatches uploadBatches) {
        if (uploadBatches == null) {
            return null;
        }

        return UploadBatchesResponse.builder()
                .id(uploadBatches.getId())
                .fileName(uploadBatches.getFileName())
                .status(uploadBatches.getStatus())
                .totalRow(uploadBatches.getTotalRow())
                .successRow(uploadBatches.getSuccessRow())
                .failRow(uploadBatches.getFailRow())
                .completedAt(uploadBatches.getCompletedAt())
                .build();
    }
}
