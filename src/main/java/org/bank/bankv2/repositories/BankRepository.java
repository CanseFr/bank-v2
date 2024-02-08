package org.bank.bankv2.repositories;

import org.bank.bankv2.models.Bank;
import org.bank.bankv2.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    @Query("from Bank where name = :name")
    List<Client> searchByFirstName(String name);

    @Query("from Bank u inner join Client a on u.id = a.bank.id where a.bank = :id") // --*
    List<Client> findAllClientByBankId(Integer id);

}

