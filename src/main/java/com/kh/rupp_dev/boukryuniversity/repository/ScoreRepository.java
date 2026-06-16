package com.kh.rupp_dev.boukryuniversity.repository;

import java.util.Optional;

import com.kh.rupp_dev.boukryuniversity.entity.Course;
import com.kh.rupp_dev.boukryuniversity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.rupp_dev.boukryuniversity.entity.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    Optional<Score> findByCourseAndStudent(Course course, Student student);

}
