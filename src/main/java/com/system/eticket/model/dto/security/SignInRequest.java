package com.system.eticket.model.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String email;

    private String username;

    private String password;
}
