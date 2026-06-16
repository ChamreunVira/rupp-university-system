package com.kh.rupp_dev.boukryuniversity.repository;

import java.util.Optional;

import com.kh.rupp_dev.boukryuniversity.entity.Class;
import com.kh.rupp_dev.boukryuniversity.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

	boolean existsByNameAndAcademicYearAndGeneration(String name, String academicYear, Integer generation);

	boolean existsByNameAndAcademicYearAndGenerationAndIdNot(String name, String academicYear, Integer generation, Long id);

	Optional<Department> findByDepartment(Department department);
}
