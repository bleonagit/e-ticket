package com.system.eticket.model.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class LawRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
}
