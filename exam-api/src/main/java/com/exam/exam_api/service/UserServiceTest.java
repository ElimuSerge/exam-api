package com.exam.exam_api.service;

import com.exam.exam_api.models.User;
import com.exam.exam_api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
// import static org.mockito.mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRole("STUDENT");
    }

    @Test
    void testRegisterUser_Success() {
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.registerUser(user);

        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals("STUDENT", savedUser.getRole());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testFindByEmail_UserExists() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        User foundUser = userService.findByEmail("test@example.com");

        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    void testFindByEmail_UserNotFound() {
        when(userRepository.findByEmail("unknown@example.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> 
            userService.findByEmail("unknown@example.com"));
    }

    @Test
    void testFindAllByRole() {
        List<User> students = Arrays.asList(user);
        when(userRepository.findByRole("STUDENT")).thenReturn(students);

        List<User> foundStudents = userService.findAllByRole("STUDENT");

        assertEquals(1, foundStudents.size());
        assertEquals("test@example.com", foundStudents.get(0).getEmail());
        verify(userRepository).findByRole("STUDENT");
    }
}