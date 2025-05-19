package com.exam.exam_api.controller;

import com.exam.exam_api.models.Course;
import com.exam.exam_api.models.CourseStudent;
import com.exam.exam_api.service.CourseService;
import com.exam.exam_api.service.CourseStudentService;
import com.exam.exam_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CourseService courseService;
    private final UserService userService;
    private final CourseStudentService courseStudentService;

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("enrollments", courseStudentService.findAll());
        return "admin_dashboard";
    }

    @GetMapping("/courses/new")
    public String showCreateCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("teachers", userService.findAllByRole("TEACHER"));
        return "create_course";
    }

    @PostMapping("/courses")
    public String createCourse(Course course, RedirectAttributes redirectAttributes) {
        courseService.saveCourse(course);
        redirectAttributes.addFlashAttribute("success", "Cours créé avec succès !");
        return "redirect:/admin";
    }

    @GetMapping("/courses/edit/{id}")
    public String showEditCourse(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        model.addAttribute("teachers", userService.findAllByRole("TEACHER"));
        return "edit_course";
    }

    @PostMapping("/courses/update")
    public String updateCourse(Course course, RedirectAttributes redirectAttributes) {
        courseService.saveCourse(course);
        redirectAttributes.addFlashAttribute("success", "Cours mis à jour avec succès !");
        return "redirect:/admin";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            courseService.deleteCourse(id);
            redirectAttributes.addFlashAttribute("success", "Cours supprimé avec succès !");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", "Impossible de supprimer le cours : il est lié à des examens ou des inscriptions.");
        }
        return "redirect:/admin";
    }

    @GetMapping("/enroll")
    public String showEnrollStudent(Model model) {
        model.addAttribute("courseStudent", new CourseStudent());
        model.addAttribute("students", userService.findAllByRole("STUDENT"));
        model.addAttribute("courses", courseService.findAll());
        return "enroll_student";
    }

    @PostMapping("/enroll")
    public String enrollStudent(CourseStudent courseStudent, RedirectAttributes redirectAttributes) {
        courseStudentService.enrollStudent(courseStudent);
        redirectAttributes.addFlashAttribute("success", "Étudiant inscrit avec succès !");
        return "redirect:/admin";
    }

    @GetMapping("/enroll/delete/{id}")
    public String deleteEnrollment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        courseStudentService.deleteEnrollment(id);
        redirectAttributes.addFlashAttribute("success", "Étudiant désinscrit avec succès !");
        return "redirect:/admin";
    }
}