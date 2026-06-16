package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.ClassRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.ClassResponse;
import com.kh.rupp_dev.boukryuniversity.entity.Class;
import com.kh.rupp_dev.boukryuniversity.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class ClassMapper {

    public Class toEntity(ClassRequest request) {
        if (request == null) {
            return null;
        }

        Class clazz = new Class();
        clazz.setName(request.getName());
        clazz.setAcademicYear(request.getAcademicYear());
        clazz.setGeneration(request.getGeneration());
        return clazz;
    }

    public void updateFromRequest(ClassRequest classRequest, Class clazz) {
        if (classRequest == null) {
            return;
        }

        clazz.setName(classRequest.getName());
        clazz.setAcademicYear(classRequest.getAcademicYear());
        clazz.setGeneration(classRequest.getGeneration());
    }

    public ClassResponse toResponse(Class clazz) {
        if (clazz == null) {
            return null;
        }

        return ClassResponse.builder()
                .id(clazz.getId())
                .name(clazz.getName())
                .departmentId(clazzDepartmentId(clazz))
                .departmentName(clazzDepartmentName(clazz))
                .academicYear(clazz.getAcademicYear())
                .generation(clazz.getGeneration())
                .creationAt(clazz.getCreationAt())
                .updatedAt(clazz.getUpdatedAt())
                .build();
    }

    private Long clazzDepartmentId(Class clazz) {
        Department department = clazz.getDepartment();
        return department != null ? department.getId() : null;
    }

    private String clazzDepartmentName(Class clazz) {
        Department department = clazz.getDepartment();
        return department != null ? department.getName() : null;
    }
}
