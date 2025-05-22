package com.exam.exam_api.controller;

import com.exam.exam_api.models.Course;
import com.exam.exam_api.models.CourseStudent;
import com.exam.exam_api.models.User;
import com.exam.exam_api.service.CourseService;
import com.exam.exam_api.service.CourseStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentRestController {
    private final CourseService courseService;
    private final CourseStudentService courseStudentService;

    @GetMapping
    public List<CourseStudent> getStudentCourses(@AuthenticationPrincipal User student) {
        return courseStudentService.findByStudent(student);
    }

    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId, @AuthenticationPrincipal User student) {
        Course course = courseService.findById(courseId);
        CourseStudent enrollment = new CourseStudent();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        courseStudentService.enrollStudent(enrollment);
        return "Inscription réussie !";
    }
}
