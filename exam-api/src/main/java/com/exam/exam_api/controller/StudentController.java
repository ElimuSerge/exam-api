package com.exam.exam_api.controller;

import com.exam.exam_api.models.Course;
import com.exam.exam_api.models.CourseStudent;
import com.exam.exam_api.models.User;
import com.exam.exam_api.service.CourseService;
import com.exam.exam_api.service.CourseStudentService;
import com.exam.exam_api.service.ExamService;
import com.exam.exam_api.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final GradeService gradeService;
    private final ExamService examService;
    private final CourseStudentService courseStudentService;
    private final CourseService courseService;

    @GetMapping
    public String studentDashboard(Model model, Authentication authentication) {
        User student = (User) authentication.getPrincipal();
        model.addAttribute("grades", gradeService.findByStudent(student));
        model.addAttribute("exams", examService.findAvailableExamsForStudent(student));
        model.addAttribute("courses", courseStudentService.findByStudent(student));
        model.addAttribute("availableCourses", courseService.findAll());
        return "student_dashboard";
    }

    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId, @AuthenticationPrincipal User student, RedirectAttributes redirectAttributes) {
        Course course = courseService.findById(courseId);
        CourseStudent enrollment = new CourseStudent();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        courseStudentService.enrollStudent(enrollment);
        redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie !");
        return "redirect:/student";
    }
}