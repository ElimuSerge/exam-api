package com.exam.exam_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testAdminDashboard() throws Exception {
        mockMvc.perform(get("/admin"))
            .andExpect(status().isOk())
            .andExpect(view().name("admin_dashboard"))
            .andExpect(model().attributeExists("courses"))
            .andExpect(model().attributeExists("enrollments"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testShowCreateCourse() throws Exception {
        mockMvc.perform(get("/admin/courses/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("create_course"))
            .andExpect(model().attributeExists("course"))
            .andExpect(model().attributeExists("teachers"));
    }
}