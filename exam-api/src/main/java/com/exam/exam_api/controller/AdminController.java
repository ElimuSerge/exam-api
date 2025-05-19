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
    public String createCourse(Course course) {
        courseService.saveCourse(course);
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
    public String updateCourse(Course course) {
        courseService.saveCourse(course);
        return "redirect:/admin";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
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
    public String enrollStudent(CourseStudent courseStudent) {
        courseStudentService.enrollStudent(courseStudent);
        return "redirect:/admin";
    }

    @GetMapping("/enroll/delete/{id}")
    public String deleteEnrollment(@PathVariable Long id) {
        courseStudentService.deleteEnrollment(id);
        return "redirect:/admin";
    }
}