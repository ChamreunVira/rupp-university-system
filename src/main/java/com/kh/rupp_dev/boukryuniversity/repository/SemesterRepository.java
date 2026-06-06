package com.kh.rupp_dev.boukryuniversity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.rupp_dev.boukryuniversity.entity.Semester;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

}
