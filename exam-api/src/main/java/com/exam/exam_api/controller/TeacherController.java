package com.exam.exam_api.controller;

import com.exam.exam_api.models.User;
import com.exam.exam_api.models.Exam;
import com.exam.exam_api.models.Grade;
import com.exam.exam_api.service.CourseService;
import com.exam.exam_api.service.ExamService;
import com.exam.exam_api.service.GradeService;
import com.exam.exam_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final CourseService courseService;
    private final ExamService examService;
    private final GradeService gradeService;
    private final UserService userService;

    @GetMapping
    public String teacherDashboard(Model model, Authentication authentication) {
        User teacher = (User) authentication.getPrincipal();
        model.addAttribute("courses", courseService.findByTeacher(teacher));
        model.addAttribute("exams", examService.findByTeacherEmail(teacher.getEmail()));
        model.addAttribute("grades", gradeService.findByTeacherEmail(teacher.getEmail()));
        return "teacher_dashboard";
    }

    @GetMapping("/exams/new")
    public String showCreateExam(Model model, Authentication authentication) {
        User teacher = (User) authentication.getPrincipal();
        model.addAttribute("exam", new Exam());
        model.addAttribute("courses", courseService.findByTeacher(teacher));
        return "create_exam";
    }

    @PostMapping("/exams")
    public String createExam(Exam exam) {
        examService.saveExam(exam);
        return "redirect:/teacher";
    }

    @GetMapping("/exams/edit/{id}")
    public String showEditExam(@PathVariable Long id, Model model) {
        Exam exam = examService.findById(id);
        model.addAttribute("exam", exam);
        model.addAttribute("courses", courseService.findAll());
        return "edit_exam";
    }

    @PostMapping("/exams/update")
    public String updateExam(Exam exam) {
        examService.saveExam(exam);
        return "redirect:/teacher";
    }

    @GetMapping("/exams/delete/{id}")
    public String deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return "redirect:/teacher";
    }

    @GetMapping("/grades/new")
    public String showCreateGrade(Model model, Authentication authentication) {
        User teacher = (User) authentication.getPrincipal();
        model.addAttribute("grade", new Grade());
        model.addAttribute("exams", examService.findByTeacherEmail(teacher.getEmail()));
        model.addAttribute("students", userService.findAllByRole("STUDENT"));
        return "create_grade";
    }

    @PostMapping("/grades")
    public String createGrade(Grade grade) {
        gradeService.saveGrade(grade);
        return "redirect:/teacher";
    }

    @GetMapping("/grades/edit/{id}")
    public String showEditGrade(@PathVariable Long id, Model model, Authentication authentication) {
        Grade grade = gradeService.findById(id);
        User teacher = (User) authentication.getPrincipal();
        model.addAttribute("grade", grade);
        model.addAttribute("exams", examService.findByTeacherEmail(teacher.getEmail()));
        model.addAttribute("students", userService.findAllByRole("STUDENT"));
        return "edit_grade";
    }

    @PostMapping("/grades/update")
    public String updateGrade(Grade grade) {
        gradeService.saveGrade(grade);
        return "redirect:/teacher";
    }

    @GetMapping("/grades/delete/{id}")
    public String deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return "redirect:/teacher";
    }

    
}