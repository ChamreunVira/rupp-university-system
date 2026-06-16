package com.kh.rupp_dev.boukryuniversity.repository;

import com.kh.rupp_dev.boukryuniversity.entity.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Long> {

    Optional<ClassSchedule> findByCourseId(Long courseId);

}
