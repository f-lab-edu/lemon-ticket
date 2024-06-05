package com.flab.lemonticket.controller;

import com.flab.lemonticket.entity.User;
import com.flab.lemonticket.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User createdUser = userService.registerUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody User user){
        User loggedInUser = userService.login(user.getEmail(), user.getPassword());
        if( loggedInUser != null ){
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
