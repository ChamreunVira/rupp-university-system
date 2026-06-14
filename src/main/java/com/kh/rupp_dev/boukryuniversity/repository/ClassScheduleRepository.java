package com.kh.rupp_dev.boukryuniversity.repository;

import com.kh.rupp_dev.boukryuniversity.entity.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Long> {

    Optional<ClassSchedule> findByCourseId(UUID courseId);

    Long id(Long id);
}
