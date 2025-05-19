package com.exam.exam_api.repository;

import com.exam.exam_api.models.Course;
import com.exam.exam_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(User teacher);
}
