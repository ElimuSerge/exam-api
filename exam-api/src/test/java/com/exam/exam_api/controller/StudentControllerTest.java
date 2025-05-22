package com.exam.exam_api.controller;

import com.exam.exam_api.models.Course;
import com.exam.exam_api.models.CourseStudent;
import com.exam.exam_api.models.User;
import com.exam.exam_api.service.CourseService;
import com.exam.exam_api.service.CourseStudentService;
import com.exam.exam_api.service.ExamService;
import com.exam.exam_api.service.GradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private CourseService courseService;

    @Mock
    private CourseStudentService courseStudentService;

    @Mock
    private ExamService examService;

    @Mock
    private GradeService gradeService;

    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user1@gmail.com", roles = "STUDENT")
    void testEnroll() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        User student = new User();
        student.setEmail("user1@gmail.com");
        student.setRole("STUDENT");

        Course course = new Course();
        course.setId(1L);
        course.setName("Mathematique");

        when(courseService.findById(1L)).thenReturn(course);
        doNothing().when(courseStudentService).enrollStudent(any(CourseStudent.class));

        mockMvc.perform(post("/student/enroll/1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/student"))
            .andExpect(flash().attribute("successMessage", "Inscription réussie !"));
    }
}