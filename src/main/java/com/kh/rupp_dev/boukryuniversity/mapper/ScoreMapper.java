package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.ScoreRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.ScoreResponse;
import com.kh.rupp_dev.boukryuniversity.entity.Course;
import com.kh.rupp_dev.boukryuniversity.entity.Score;
import com.kh.rupp_dev.boukryuniversity.entity.Student;
import com.kh.rupp_dev.boukryuniversity.entity.composite.CourseId;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScoreMapper {

    public Score toEntity(ScoreRequest request) {
        if (request == null) {
            return null;
        }

        Score score = new Score();
        score.setScore(BigDecimal.valueOf(request.getScore()));
        score.setGrade(request.getGrade());
        score.setStatus(request.isStatus());
        return score;
    }

    public ScoreResponse toResponse(Score score) {
        if (score == null) {
            return null;
        }

        return ScoreResponse.builder()
                .id(score.getId())
                .studentId(scoreStudentId(score))
                .semesterId(scoreCourseSemesterId(score))
                .subjectId(scoreCourseSubjectId(score))
                .score(score.getScore())
                .creationAt(score.getCreationAt())
                .updatedAt(score.getUpdatedAt())
                .status(score.isStatus())
                .build();
    }

    public List<ScoreResponse> toResponseList(List<Score> scores) {
        if (scores == null) {
            return null;
        }

        List<ScoreResponse> list = new ArrayList<>(scores.size());
        for (Score score : scores) {
            list.add(toResponse(score));
        }
        return list;
    }

    public void updateFromRequest(ScoreRequest request, Score score) {
        if (request == null) {
            return;
        }

        score.setScore(BigDecimal.valueOf(request.getScore()));
        score.setGrade(request.getGrade());
        score.setStatus(request.isStatus());
    }

    private Long scoreStudentId(Score score) {
        Student student = score.getStudent();
        return student != null ? student.getId() : null;
    }

    private Long scoreCourseSemesterId(Score score) {
        CourseId courseId = courseId(score);
        return courseId != null ? courseId.getSemesterId() : null;
    }

    private Long scoreCourseSubjectId(Score score) {
        CourseId courseId = courseId(score);
        return courseId != null ? courseId.getSubjectId() : null;
    }

    private CourseId courseId(Score score) {
        Course course = score.getCourse();
        return course != null ? course.getCourseId() : null;
    }
}
