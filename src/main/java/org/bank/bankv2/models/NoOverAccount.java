package org.bank.bankv2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "account_id")
public class NoOverAccount extends Account{
}
