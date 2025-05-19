package com.exam.exam_api.repository;

import com.exam.exam_api.models.Course;
import com.exam.exam_api.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByCourse(Course course);

    @Query("SELECT e FROM Exam e WHERE e.course.teacher.email = :email")
    List<Exam> findByTeacherEmail(String email);

    @Query("SELECT e FROM Exam e WHERE e.course.id IN (SELECT cs.course.id FROM CourseStudent cs WHERE cs.student.id = :studentId) AND e.id NOT IN (SELECT g.exam.id FROM Grade g WHERE g.student.id = :studentId)")
    List<Exam> findAvailableExamsForStudent(Long studentId);
}