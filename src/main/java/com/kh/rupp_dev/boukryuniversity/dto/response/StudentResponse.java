package com.kh.rupp_dev.boukryuniversity.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudentResponse {

    private Integer id;

    private Integer departmentId;
    
    private String departmentName;

    private Integer classId;
    
    private String className;

    private String studentCode;

    private String khFirstName;

    private String khLastName;

    private String enFirstName;

    private String enLastName;

    private String gender;

    private LocalDate dateOfBirth;

    private LocalDate enrollmentDate;

    private String email;

    private String phoneNumber;

    private StudentAddressResponse address;

    private boolean status;

    private LocalDate creationAt;

    private LocalDate updatedAt;
}
