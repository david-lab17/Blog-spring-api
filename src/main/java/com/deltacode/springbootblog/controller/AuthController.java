package com.deltacode.springbootblog.controller;

import com.deltacode.springbootblog.entity.Role;
import com.deltacode.springbootblog.entity.UserApp;
import com.deltacode.springbootblog.payload.JWTAuthResponse;
import com.deltacode.springbootblog.payload.LoginDto;
import com.deltacode.springbootblog.payload.SignUpDto;
import com.deltacode.springbootblog.repository.RoleRepository;
import com.deltacode.springbootblog.repository.UserRepository;
import com.deltacode.springbootblog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse>authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                 loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //get token from token provider
        String token= tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));

    }
    @PostMapping("/signup")
    public ResponseEntity<?>registerUser(@RequestBody SignUpDto signUpDto){
        //check if user already exists by username or email
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username already exists",HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email already exists",HttpStatus.BAD_REQUEST);
        }
        //create new user object
        UserApp userApp = new UserApp();
        userApp.setUsername(signUpDto.getUsername());
        userApp.setEmail(signUpDto.getEmail());
        userApp.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        userApp.setName(signUpDto.getName());

        Role userRole = roleRepository.findByName("ROLE_ADMIN").get();
        userApp.setRoles(Collections.singleton(userRole));
        userRepository.save(userApp);
        return ResponseEntity.ok("User registered successfully");

    }

}
