package com.exam.exam_api.repository;

import com.exam.exam_api.models.Grade;
import com.exam.exam_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(User student);

    @Query("SELECT g FROM Grade g WHERE g.exam.course.teacher.email = :email")
    List<Grade> findByTeacherEmail(String email);

    boolean existsByExamId(Long examId);
}