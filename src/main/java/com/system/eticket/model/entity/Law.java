package com.system.eticket.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Table(name = "LAWS")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tickets"})
@EqualsAndHashCode(exclude = {"tickets"})
public class Law implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "law_generator")
    @SequenceGenerator(name = "law_generator", sequenceName = "LAWS_SEQUENCE_ID", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(name = "TICKET_LAW", joinColumns = @JoinColumn(name = "LAW_ID"), inverseJoinColumns = @JoinColumn(name = "TICKET_ID"))
    private Set<Ticket> tickets;
}
