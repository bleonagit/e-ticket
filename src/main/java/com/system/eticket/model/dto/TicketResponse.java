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
public class TicketResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String serialNumber;

    private double amount;

    private String ticketPlace;

    private String plateId;

    private String breaker;

    private ZonedDateTime ticketDate;

    private ZonedDateTime createdDate;

    private String vehicleType;

    private OfficialResponse official;

    private Set<LawResponse> laws;
}
