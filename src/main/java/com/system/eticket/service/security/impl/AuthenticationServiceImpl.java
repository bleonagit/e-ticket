package com.system.eticket.service.security.impl;

import com.system.eticket.model.dto.security.JwtAuthenticationResponse;
import com.system.eticket.model.dto.security.SignUpRequest;
import com.system.eticket.model.dto.security.SignInRequest;
import com.system.eticket.model.entity.user.Role;
import com.system.eticket.model.entity.user.User;
import com.system.eticket.persistence.UserRepository;
import com.system.eticket.service.security.AuthenticationService;
import com.system.eticket.service.security.JwtService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtService jwtService;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) throws IllegalArgumentException{
        var user = userRepository.findUserByUsernameOrEmail(request.getUsername(), request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid username/email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}