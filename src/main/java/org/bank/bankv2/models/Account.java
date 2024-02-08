package org.bank.bankv2.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    private Integer id;

    private Float solde;

    @ManyToOne
    @JoinColumn(name = "id_bank")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    private Client client;
}
