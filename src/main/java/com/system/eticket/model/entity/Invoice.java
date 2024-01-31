package com.system.eticket.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "INVOICES")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Invoice implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_generator")
    @SequenceGenerator(name = "invoice_generator", sequenceName = "INVOICES_SEQUENCE_ID", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SERIAL_NUMBER", unique = true)
    private String serialNumber;

    @Column(name = "INITIAL_AMOUNT")
    private double initialAmount;

    @Column(name = "DISCOUNT")
    private double discount;

    @Column(name = "FEE")
    private double fee;

    @Column(name = "PAYED_AMOUNT")
    private double payedAmount;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    @OneToOne
    @JoinColumn(name = "TICKET_ID", referencedColumnName = "ID")
    private Ticket ticket;
}
