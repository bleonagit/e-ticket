package com.system.eticket.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InvoiceResponse {
    private String serialNumber;

    private double initialAmount;

    private double discount;

    private double fee;

    private double payedAmount;

    private Timestamp createdDate;

    private TicketResponse ticket;
}
