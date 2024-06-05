package com.flab.lemonticket.service;

import com.flab.lemonticket.entity.User;
import com.flab.lemonticket.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final Logger log = LoggerFactory.getLogger(getClass());

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user){
        if( userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if( user != null && passwordEncoder.matches(password, user.getPassword())){
            return user;
        }
        return null;
    }
}
