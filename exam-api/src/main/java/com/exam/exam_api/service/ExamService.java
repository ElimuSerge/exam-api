package com.exam.exam_api.service;

import com.exam.exam_api.models.Course;
import com.exam.exam_api.models.Exam;
import com.exam.exam_api.models.User;
import com.exam.exam_api.repository.ExamRepository;
import com.exam.exam_api.repository.GradeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final GradeRepository gradeRepository;

    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam findById(Long id) {
        return examRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Exam not found with id: " + id));
    }

    // public void deleteExam(Long id) {
    //     examRepository.deleteById(id);
    // }

    public void deleteExam(Long id) {
    if (!gradeRepository.existsByExamId(id)) {
        examRepository.deleteById(id);
    } else {
        throw new IllegalStateException("Cannot delete exam with existing grades");
    }
    }

    public List<Exam> findByCourse(Course course) {
        return examRepository.findByCourse(course);
    }

    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    public List<Exam> findByTeacherEmail(String email) {
        return examRepository.findByTeacherEmail(email);
    }

    public List<Exam> findAvailableExamsForStudent(User student) {
        return examRepository.findAvailableExamsForStudent(student.getId());
    }
}