package com.system.eticket.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Table(name = "OFFICIALS")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tickets"})
@EqualsAndHashCode(exclude = {"tickets"})
public class Official implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "official_generator")
    @SequenceGenerator(name = "official_generator", sequenceName = "OFFICIAL_SEQUENCE_ID", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTHDATE")
    private Date birthdate;

    @OneToMany(mappedBy = "official")
    private Set<Ticket> tickets;
}
