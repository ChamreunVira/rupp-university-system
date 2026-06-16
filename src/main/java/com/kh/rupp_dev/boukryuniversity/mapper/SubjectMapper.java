package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.SubjectRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.SubjectResponse;
import com.kh.rupp_dev.boukryuniversity.entity.Department;
import com.kh.rupp_dev.boukryuniversity.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public Subject toEntity(SubjectRequest request) {
        if (request == null) {
            return null;
        }

        Subject subject = new Subject();
        subject.setName(request.getName());
        subject.setDescription(request.getDescription());
        return subject;
    }

    public SubjectResponse toResponse(Subject subject) {
        if (subject == null) {
            return null;
        }

        return SubjectResponse.builder()
                .id(subject.getId())
                .departmentId(subjectDepartmentId(subject))
                .departmentName(subjectDepartmentName(subject))
                .thumbnail(subject.getThumbnail())
                .name(subject.getName())
                .description(subject.getDescription())
                .code(subject.getCode())
                .build();
    }

    public void updateFromRequest(SubjectRequest request, Subject subject) {
        if (request == null) {
            return;
        }

        if (request.getName() != null) {
            subject.setName(request.getName());
        }
        if (request.getDescription() != null) {
            subject.setDescription(request.getDescription());
        }
    }

    private Long subjectDepartmentId(Subject subject) {
        Department department = subject.getDepartment();
        return department != null ? department.getId() : null;
    }

    private String subjectDepartmentName(Subject subject) {
        Department department = subject.getDepartment();
        return department != null ? department.getName() : null;
    }
}
