package com.flab.lemonticket.controller;

import com.flab.lemonticket.entity.AuthenticationRequest;
import com.flab.lemonticket.entity.User;
import com.flab.lemonticket.repository.UserRepository;
import com.flab.lemonticket.service.FileUploadService;
import com.flab.lemonticket.service.JwtTokenService;
import com.flab.lemonticket.service.JwtUtil;
import com.flab.lemonticket.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {


    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private JwtTokenService jwtTokenService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestPart("user") User user, @RequestPart("file") MultipartFile file) throws IOException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        if (!file.isEmpty()) {
            String filePath = fileUploadService.saveFile(file, savedUser.getUserId());
            savedUser.setProfileImg(filePath);
            userRepository.save(savedUser);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("user", savedUser);

        return ResponseEntity.ok(response);
    }


    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        Map<String, Object> response = new HashMap<>();
        response.put("jwt", jwt);
        response.put("userDetails", userDetails);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(@RequestHeader("Authorization") String token){
        String jwtToken = token.substring(7);
        jwtTokenService.invalidateToken(jwtToken);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully logged out");
        return ResponseEntity.ok(response);
    }


    

    @GetMapping("/test_normal")
    public ResponseEntity<Map<String, Object>> testNormal() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "test world");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test_event_admin")
    public ResponseEntity<Map<String, Object>> testEventAdmin() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "test world");
        return ResponseEntity.ok(response);
    }



    
    @GetMapping("/test_no_auth")
    public ResponseEntity<Map<String, Object>> test_no_auth(){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "test world");
        return ResponseEntity.ok(response);
    }
}
