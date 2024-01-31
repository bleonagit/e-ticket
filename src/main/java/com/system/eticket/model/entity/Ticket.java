package com.system.eticket.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Table(name = "TICKETS")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"laws"})
@EqualsAndHashCode(exclude = {"laws"})
public class Ticket implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_generator")
    @SequenceGenerator(name = "ticket_generator", sequenceName = "TICKETS_SEQUENCE_ID", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "TICKET_PLACE")
    private String ticketPlace;

    @Column(name = "PLATE_ID")
    private String plateId;

    @Column(name = "BREAKER")
    private String breaker;

    @Column(name = "TICKET_DATE")
    private ZonedDateTime ticketDate;

    @Column(name = "CREATED_DATE")
    private ZonedDateTime createdDate;

    @Column(name = "VEHICLE_TYPE")
    private String vehicleType;

    @ManyToOne
    @JoinColumn(name = "OFFICIAL_ID")
    private Official official;

    @ManyToMany
    @JoinTable(name = "TICKET_LAW", joinColumns = @JoinColumn(name = "TICKET_ID"), inverseJoinColumns = @JoinColumn(name = "LAW_ID"))
    private Set<Law> laws;

    @OneToOne(mappedBy = "ticket")
    private Invoice invoice;
}
