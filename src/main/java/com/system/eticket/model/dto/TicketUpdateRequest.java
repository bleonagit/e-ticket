package com.system.eticket.model.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
@Schema
@Builder
public class TicketUpdateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Parameter(description = "Ticket serial number", required = true)
    private String serialNumber;

    private double amount;

    private String ticketPlace;

    private String plateId;

    private String breaker;

    private ZonedDateTime ticketDate;

    private String vehicleType;

    private OfficialRequest official;

    private Set<LawRequest> laws;
}
