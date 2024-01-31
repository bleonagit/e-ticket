package com.system.eticket.service.security;

import com.system.eticket.model.dto.security.JwtAuthenticationResponse;
import com.system.eticket.model.dto.security.SignUpRequest;
import com.system.eticket.model.dto.security.SignInRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request) throws IllegalArgumentException;
}
