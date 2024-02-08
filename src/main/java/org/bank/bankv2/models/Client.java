package org.bank.bankv2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Client  {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String username;

    private String adress;

    @ManyToOne
    @JoinColumn(name = "id_bank")
    private Bank bank;

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    @ToString.Exclude
    @JsonIgnore
    private List<Account> listAccounts;
}