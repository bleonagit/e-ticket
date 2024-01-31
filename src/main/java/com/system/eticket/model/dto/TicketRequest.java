package com.system.eticket.model.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TicketRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private double amount;

    private String ticketPlace;

    private String plateId;

    private String breaker;

    private ZonedDateTime ticketDate;

    private String vehicleType;

    private OfficialRequest official;

    private Set<LawRequest> laws;
}
