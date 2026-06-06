package com.kh.rupp_dev.boukryuniversity.service;


import com.kh.rupp_dev.boukryuniversity.dto.request.ClassRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.ClassResponse;
import com.kh.rupp_dev.boukryuniversity.dto.response.DepartmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassService {

	ClassResponse create(ClassRequest request);

	Page<ClassResponse> getAll(Pageable pageable);

	ClassResponse getById(Integer id);

	ClassResponse update(Integer id, ClassRequest request);

    void delete(Integer id);

	@Deprecated
	DepartmentResponse findByDepartmentId(Integer departmentId , Integer classId);

}
