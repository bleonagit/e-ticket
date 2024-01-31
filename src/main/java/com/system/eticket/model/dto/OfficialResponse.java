package com.system.eticket.model.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfficialResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private Date birthdate;
}
