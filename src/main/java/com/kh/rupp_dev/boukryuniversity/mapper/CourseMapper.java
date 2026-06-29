package com.kh.rupp_dev.boukryuniversity.mapper;

import com.kh.rupp_dev.boukryuniversity.dto.request.CourseRequest;
import com.kh.rupp_dev.boukryuniversity.dto.response.CourseResponse;
import com.kh.rupp_dev.boukryuniversity.entity.Course;
import com.kh.rupp_dev.boukryuniversity.entity.User;
import com.kh.rupp_dev.boukryuniversity.entity.composite.CourseId;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request) {
        if (request == null) {
            return null;
        }

        Course course = new Course();
        course.setCourseId(toCourseId(request));
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        return course;
    }

    public CourseResponse toResponse(Course entity) {
        if (entity == null) {
            return null;
        }

        CourseResponse.CourseResponseBuilder builder = CourseResponse.builder()
                .semesterId(entityCourseSemesterId(entity))
                .subjectId(entityCourseSubjectId(entity))
                .instructorId(entityInstructorId(entity))
                .instructorName(entityInstructorFullName(entity))
                .name(entity.getName())
                .description(entity.getDescription());

        if (entity.getStartAt() != null) {
            builder.startAt(DateTimeFormatter.ISO_LOCAL_DATE.format(entity.getStartAt()));
        }
        if (entity.getEndAt() != null) {
            builder.endAt(DateTimeFormatter.ISO_LOCAL_DATE.format(entity.getEndAt()));
        }

        return builder.build();
    }

    public void updateFromRequest(CourseRequest request, Course course) {
        if (request == null) {
            return;
        }

        course.setName(request.getName());
        course.setDescription(request.getDescription());
    }

    public List<CourseResponse> toList(List<Course> courses) {
        if (courses == null) {
            return null;
        }

        List<CourseResponse> list = new ArrayList<>(courses.size());
        for (Course course : courses) {
            list.add(toResponse(course));
        }
        return list;
    }

    private CourseId toCourseId(CourseRequest request) {
        CourseId courseId = new CourseId();
        courseId.setSemesterId(request.getSemesterId());
        courseId.setSubjectId(request.getSubjectId());
        return courseId;
    }

    private Long entityCourseSemesterId(Course course) {
        CourseId courseId = course.getCourseId();
        return courseId != null ? courseId.getSemesterId() : null;
    }

    private Long entityCourseSubjectId(Course course) {
        CourseId courseId = course.getCourseId();
        return courseId != null ? courseId.getSubjectId() : null;
    }

    private Long entityInstructorId(Course course) {
        User instructor = course.getInstructor();
        return instructor != null ? instructor.getId() : null;
    }

    private String entityInstructorFullName(Course course) {
        User instructor = course.getInstructor();
        return instructor != null ? instructor.getFullName() : null;
    }
}
