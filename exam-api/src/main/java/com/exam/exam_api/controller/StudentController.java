package com.exam.exam_api.controller;

import com.exam.exam_api.models.User;
import com.exam.exam_api.service.CourseStudentService;
import com.exam.exam_api.service.ExamService;
import com.exam.exam_api.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final GradeService gradeService;
    private final ExamService examService;
    private final CourseStudentService courseStudentService;

    @GetMapping
    public String studentDashboard(Model model, Authentication authentication) {
        User student = (User) authentication.getPrincipal();
        model.addAttribute("grades", gradeService.findByStudent(student));
        model.addAttribute("exams", examService.findAvailableExamsForStudent(student));
        model.addAttribute("courses", courseStudentService.findByStudent(student));
        return "student_dashboard";
    }
}