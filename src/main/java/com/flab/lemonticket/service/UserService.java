package com.flab.lemonticket.service;

import com.flab.lemonticket.entity.User;
import com.flab.lemonticket.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private final Logger log = LoggerFactory.getLogger(getClass());

}
