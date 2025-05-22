package com.exam.exam_api.controller;

import com.exam.exam_api.models.Course;
import com.exam.exam_api.models.Exam;
import com.exam.exam_api.models.Grade;
import com.exam.exam_api.models.User;
import com.exam.exam_api.service.CourseService;
import com.exam.exam_api.service.ExamService;
import com.exam.exam_api.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherRestController {
    private final CourseService courseService;
    private final ExamService examService;
    private final GradeService gradeService;

    @GetMapping
    public List<Course> getTeacherCourses(@AuthenticationPrincipal User teacher) {
        return courseService.findByTeacher(teacher);
    }

    @GetMapping("/exams")
    public List<Exam> getTeacherExams(@AuthenticationPrincipal User teacher) {
        return examService.findByTeacherEmail(teacher.getEmail());
    }

    @GetMapping("/grades")
    public List<Grade> getTeacherGrades(@AuthenticationPrincipal User teacher) {
        return gradeService.findByTeacherEmail(teacher.getEmail());
    }

    @PostMapping("/exams")
    public String createExam(@RequestBody Exam exam) {
        examService.saveExam(exam);
        return "Examen créé avec succès !";
    }

    @PostMapping("/grades")
    public String createGrade(@RequestBody Grade grade) {
        gradeService.saveGrade(grade);
        return "Note créée avec succès !";
    }
}