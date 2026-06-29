package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.StudentRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.StudentResponse;
import com.kh.rupp_dev.boukryuniversity.entity.Class;
import com.kh.rupp_dev.boukryuniversity.entity.Department;
import com.kh.rupp_dev.boukryuniversity.entity.Student;
import com.kh.rupp_dev.boukryuniversity.entity.StudentAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final StudentAddressMapper studentAddressMapper;

    public Student toEntity(StudentRequest request) {
        if (request == null) {
            return null;
        }

        Student student = new Student();
        student.setKhFirstName(request.getKhFirstName());
        student.setKhLastName(request.getKhLastName());
        student.setEnFirstName(request.getEnFirstName());
        student.setEnLastName(request.getEnLastName());
        student.setGender(request.getGender());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setAddress(studentAddressMapper.toEntity(request.getAddress()));
        return student;
    }

    public StudentResponse toResponse(Student student) {
        if (student == null) {
            return null;
        }

        StudentResponse.StudentResponseBuilder builder = StudentResponse.builder()
                .id(student.getId())
                .departmentId(studentClazzDepartmentId(student))
                .departmentName(studentClazzDepartmentName(student))
                .classId(studentClazzId(student))
                .className(studentClazzName(student))
                .studentCode(student.getStudentCode())
                .khFirstName(student.getKhFirstName())
                .khLastName(student.getKhLastName())
                .enFirstName(student.getEnFirstName())
                .enLastName(student.getEnLastName())
                .gender(student.getGender())
                .dateOfBirth(student.getDateOfBirth())
                .enrollmentDate(student.getEnrollmentDate())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .address(studentAddressMapper.toResponse(student.getAddress()))
                .creationAt(student.getCreationAt())
                .updatedAt(student.getUpdatedAt());

        if (student.getStatus() != null) {
            builder.status(student.getStatus());
        }

        return builder.build();
    }

    public List<StudentResponse> toList(List<Student> students) {
        if (students == null) {
            return null;
        }

        List<StudentResponse> list = new ArrayList<>(students.size());
        for (Student student : students) {
            list.add(toResponse(student));
        }
        return list;
    }

    public void updateFromRequest(StudentRequest request, Student student) {
        if (request == null) {
            return;
        }

        if (request.getAddress() != null) {
            if (student.getAddress() == null) {
                student.setAddress(new StudentAddress());
            }
            studentAddressMapper.updateFromRequest(request.getAddress(), student.getAddress());
        } else {
            student.setAddress(null);
        }

        student.setKhFirstName(request.getKhFirstName());
        student.setKhLastName(request.getKhLastName());
        student.setEnFirstName(request.getEnFirstName());
        student.setEnLastName(request.getEnLastName());
        student.setGender(request.getGender());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
    }

    private Long studentClazzDepartmentId(Student student) {
        Class clazz = student.getClazz();
        if (clazz == null) {
            return null;
        }
        Department department = clazz.getDepartment();
        return department != null ? department.getId() : null;
    }

    private String studentClazzDepartmentName(Student student) {
        Class clazz = student.getClazz();
        if (clazz == null) {
            return null;
        }
        Department department = clazz.getDepartment();
        return department != null ? department.getName() : null;
    }

    private Long studentClazzId(Student student) {
        Class clazz = student.getClazz();
        return clazz != null ? clazz.getId() : null;
    }

    private String studentClazzName(Student student) {
        Class clazz = student.getClazz();
        return clazz != null ? clazz.getName() : null;
    }
}
