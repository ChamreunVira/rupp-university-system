package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.response.StudentsFilterResponse;
import com.kh.rupp_dev.boukryuniversity.entity.Class;
import com.kh.rupp_dev.boukryuniversity.entity.Department;
import com.kh.rupp_dev.boukryuniversity.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentFilterMapper {

    public StudentsFilterResponse toResponse(Student student) {
        if (student == null) {
            return null;
        }

        return StudentsFilterResponse.builder()
                .studentId(student.getId())
                .studentCode(student.getStudentCode())
                .gender(student.getGender())
                .className(studentClazzName(student))
                .classId(studentClazzId(student))
                .departmentId(studentClazzDepartmentId(student))
                .khFirstName(student.getKhFirstName())
                .khLastName(student.getKhLastName())
                .enFirstName(student.getEnFirstName())
                .enLastName(student.getEnLastName())
                .build();
    }

    private String studentClazzName(Student student) {
        Class clazz = student.getClazz();
        return clazz != null ? clazz.getName() : null;
    }

    private Long studentClazzId(Student student) {
        Class clazz = student.getClazz();
        return clazz != null ? clazz.getId() : null;
    }

    private Long studentClazzDepartmentId(Student student) {
        Class clazz = student.getClazz();
        if (clazz == null) {
            return null;
        }
        Department department = clazz.getDepartment();
        return department != null ? department.getId() : null;
    }
}
