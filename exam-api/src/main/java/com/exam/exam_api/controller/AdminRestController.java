// package com.exam.exam_api.controller;

// import com.exam.exam_api.models.Course;
// import com.exam.exam_api.models.CourseStudent;
// import com.exam.exam_api.service.CourseService;
// import com.exam.exam_api.service.CourseStudentService;
// import com.exam.exam_api.service.UserService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/admin")
// @RequiredArgsConstructor
// public class AdminRestController {
//     private final CourseService courseService;
//     private final UserService userService;
//     private final CourseStudentService courseStudentService;

//     @GetMapping("/courses")
//     public List<Course> getAllCourses() {
//         return courseService.findAll();
//     }

//     @PostMapping("/courses")
//     public String createCourse(@RequestBody Course course) {
//         courseService.saveCourse(course);
//         return "Cours créé avec succès !";
//     }

//     @PutMapping("/courses/{id}")
//     public String updateCourse(@PathVariable Long id, @RequestBody Course course) {
//         course.setId(id);
//         courseService.saveCourse(course);
//         return "Cours mis à jour avec succès !";
//     }

//     @DeleteMapping("/courses/{id}")
//     public String deleteCourse(@PathVariable Long id) {
//         try {
//             courseService.deleteCourse(id);
//             return "Cours supprimé avec succès !";
//         } catch (IllegalStateException e) {
//             return "Erreur : Cours lié à des examens ou inscriptions.";
//         }
//     }

//     @GetMapping("/enrollments")
//     public List<CourseStudent> getAllEnrollments() {
//         return courseStudentService.findAll();
//     }

//     @PostMapping("/enroll")
//     public String enrollStudent(@RequestBody CourseStudent courseStudent) {
//         courseStudentService.enrollStudent(courseStudent);
//         return "Étudiant inscrit avec succès !";
//     }

//     @DeleteMapping("/enroll/{id}")
//     public String deleteEnrollment(@PathVariable Long id) {
//         courseStudentService.deleteEnrollment(id);
//         return "Étudiant désinscrit avec succès !";
//     }
// }