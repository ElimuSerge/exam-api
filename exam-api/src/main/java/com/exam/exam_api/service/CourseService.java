package com.exam.exam_api.service;

import com.exam.exam_api.models.Course;
import com.exam.exam_api.models.User;
import com.exam.exam_api.repository.CourseRepository;
import com.exam.exam_api.repository.CourseStudentRepository;
import com.exam.exam_api.repository.ExamRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ExamRepository examRepository; // Ajouté
    private final CourseStudentRepository courseStudentRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + id));
    }

    // public void deleteCourse(Long id) {
    //     courseRepository.deleteById(id);
    // }

    public void deleteCourse(Long id) {
    if (!examRepository.existsByCourseId(id) && !courseStudentRepository.existsByCourseId(id)) {
        courseRepository.deleteById(id);
    } else {
        throw new IllegalStateException("Cannot delete course with existing exams or enrollments");
    }
    }

    public List<Course> findByTeacher(User teacher) {
        return courseRepository.findByTeacher(teacher);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}