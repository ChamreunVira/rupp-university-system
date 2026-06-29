package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.SemesterRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.SemesterResponse;
import com.kh.rupp_dev.boukryuniversity.entity.Semester;
import org.springframework.stereotype.Component;

@Component
public class SemesterMapper {

    public Semester toEntity(SemesterRequest request) {
        if (request == null) {
            return null;
        }

        Semester semester = new Semester();
        semester.setName(request.getName());
        semester.setDescription(request.getDescription());
        return semester;
    }

    public SemesterResponse toResponse(Semester semester) {
        if (semester == null) {
            return null;
        }

        return SemesterResponse.builder()
                .id(semester.getId())
                .name(semester.getName())
                .startDate(semester.getStartDate())
                .endDate(semester.getEndDate())
                .description(semester.getDescription())
                .build();
    }

    public void updateFromRequest(SemesterRequest request, Semester semester) {
        if (request == null) {
            return;
        }

        if (request.getName() != null) {
            semester.setName(request.getName());
        }
        if (request.getDescription() != null) {
            semester.setDescription(request.getDescription());
        }
    }
}
