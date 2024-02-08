package org.bank.bankv2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Bank {
    @Id
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "bank")
    @JsonBackReference
    @ToString.Exclude
    @JsonIgnore
    private List<Client> listClients;

    @OneToMany(mappedBy = "bank")
    @JsonBackReference
    @ToString.Exclude
    @JsonIgnore
    private List<Account> listAccounts;

}
