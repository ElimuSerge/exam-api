package com.exam.exam_api.service;

import com.exam.exam_api.models.Grade;
import com.exam.exam_api.models.User;
import com.exam.exam_api.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Grade findById(Long id) {
        return gradeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Grade not found with id: " + id));
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }

    public List<Grade> findByStudent(User student) {
        return gradeRepository.findByStudent(student);
    }

    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    public List<Grade> findByTeacherEmail(String email) {
        return gradeRepository.findByTeacherEmail(email);
    }
}