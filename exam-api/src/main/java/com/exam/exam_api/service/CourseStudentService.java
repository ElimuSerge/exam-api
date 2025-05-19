package com.exam.exam_api.service;

import com.exam.exam_api.models.CourseStudent;
import com.exam.exam_api.models.User;
import com.exam.exam_api.repository.CourseStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseStudentService {
    private final CourseStudentRepository courseStudentRepository;

    public void enrollStudent(CourseStudent courseStudent) {
        courseStudentRepository.save(courseStudent);
    }

    public void deleteEnrollment(Long id) {
        courseStudentRepository.deleteById(id);
    }

    public List<CourseStudent> findByStudent(User student) {
        return courseStudentRepository.findByStudent(student);
    }

    public List<CourseStudent> findAll() {
        return courseStudentRepository.findAll();
    }
}