package com.system.eticket.model.dto.security;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String token;
}
