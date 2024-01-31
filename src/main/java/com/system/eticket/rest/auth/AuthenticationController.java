package com.system.eticket.rest.auth;

import com.system.eticket.model.dto.security.JwtAuthenticationResponse;
import com.system.eticket.model.dto.security.SignUpRequest;
import com.system.eticket.model.dto.security.SignInRequest;
import com.system.eticket.service.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Resource
    private AuthenticationService authenticationService;

    @PostMapping(value = "/signUp" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Sign up for Users", description = "Sign upp API for all regular Users")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User registered successfully!"),
                    @ApiResponse(responseCode = "500", description = "Internal server error!")
            }
    )
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signIn")
    @Operation(summary = "Sign in for Users", description = "Sign in API for all regular Users")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User signed in successfully!"),
                    @ApiResponse(responseCode = "500", description = "Internal server error!")
            }
    )
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) throws IllegalArgumentException{
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
