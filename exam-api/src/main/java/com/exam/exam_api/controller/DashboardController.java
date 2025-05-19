package com.exam.exam_api.controller;

// import com.exam.exam_api.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.contains("ADMIN")) {
            return "redirect:/admin";
        } else if (role.contains("TEACHER")) {
            return "redirect:/teacher";
        } else {
            return "redirect:/student";
        }
    }
}
