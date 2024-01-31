package com.system.eticket.model.dto.security;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;
}