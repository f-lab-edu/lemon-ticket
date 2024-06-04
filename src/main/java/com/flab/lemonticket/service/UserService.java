package com.flab.lemonticket.service;

import com.flab.lemonticket.entity.User;
import com.flab.lemonticket.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user){
        if( userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //return userRepository.save(user);
        User result = userRepository.save(user);
        if( result != null ){
            System.out.println("ok");
        } else {
            System.out.println("null");
        }
        return result;
    }
}
