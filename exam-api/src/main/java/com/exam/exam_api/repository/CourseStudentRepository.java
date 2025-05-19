package com.exam.exam_api.repository;

import com.exam.exam_api.models.CourseStudent;
import com.exam.exam_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
    List<CourseStudent> findByStudent(User student);

    boolean existsByCourseId(Long courseId);
}