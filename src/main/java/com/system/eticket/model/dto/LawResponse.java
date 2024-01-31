package com.system.eticket.model.dto;

import com.system.eticket.model.entity.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tickets"})
public class LawResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String title;

    private String description;
}
