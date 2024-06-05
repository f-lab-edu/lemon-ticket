package com.flab.lemonticket.controller;

import com.flab.lemonticket.entity.User;
import com.flab.lemonticket.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Transactional
class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void registerUser_ShouldReturnCreatedUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole("USER");
        user.setUsername("TEST");

        User createdUser = userService.registerUser(user);
        assertNotNull(createdUser);
    }

    @Test
    void login_Test(){
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole("USER");
        user.setUsername("TEST");

        String password = user.getPassword();
        User createdUser = userService.registerUser(user);
        assertNotNull(createdUser);

        User loggedInUser = userService.login(user.getEmail(), password);
        assertNotNull(loggedInUser);
    }
}