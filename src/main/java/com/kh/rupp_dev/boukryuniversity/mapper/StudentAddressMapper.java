package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.StudentAddressRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.StudentAddressResponse;
import com.kh.rupp_dev.boukryuniversity.entity.StudentAddress;
import org.springframework.stereotype.Component;

@Component
public class StudentAddressMapper {

    public StudentAddress toEntity(StudentAddressRequest request) {
        if (request == null) {
            return null;
        }

        StudentAddress studentAddress = new StudentAddress();
        studentAddress.setHouseNumber(request.getHouseNumber());
        studentAddress.setStreet(request.getStreet());
        studentAddress.setSangkat(request.getSangkat());
        studentAddress.setKhan(request.getKhan());
        studentAddress.setProvince(request.getProvince());
        studentAddress.setCountry(request.getCountry());
        return studentAddress;
    }

    public StudentAddressResponse toResponse(StudentAddress entity) {
        if (entity == null) {
            return null;
        }

        return StudentAddressResponse.builder()
                .id(entity.getId())
                .houseNumber(entity.getHouseNumber())
                .street(entity.getStreet())
                .sangkat(entity.getSangkat())
                .khan(entity.getKhan())
                .province(entity.getProvince())
                .country(entity.getCountry())
                .build();
    }

    public void updateFromRequest(StudentAddressRequest request, StudentAddress entity) {
        if (request == null) {
            return;
        }

        entity.setId(request.getId());
        entity.setHouseNumber(request.getHouseNumber());
        entity.setStreet(request.getStreet());
        entity.setSangkat(request.getSangkat());
        entity.setKhan(request.getKhan());
        entity.setProvince(request.getProvince());
        entity.setCountry(request.getCountry());
    }
}
