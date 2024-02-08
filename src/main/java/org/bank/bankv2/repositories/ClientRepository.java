package org.bank.bankv2.repositories;

import org.bank.bankv2.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("from Client where username = :username")
    List<Client> searchByFirstName(String username);

    @Query("from Client where adress = :adress")
    List<Client> searchByAdress(String adress);
}
