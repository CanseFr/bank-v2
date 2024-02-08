package org.bank.bankv2;

import lombok.AllArgsConstructor;
import org.bank.bankv2.models.Bank;
import org.bank.bankv2.models.Client;
import org.bank.bankv2.services.BankService;
import org.bank.bankv2.services.Impl.BankServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@AllArgsConstructor
public class BankV2Application {


    public static void main(String[] args) {
        SpringApplication.run(BankV2Application.class, args);
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("Bnp");

        Client client1 = new Client();
        client1.setId(1);
        client1.setAdress("rue de ");
        client1.setUsername("Canse");

        Client client2 = new Client();
        client2.setId(2);
        client2.setAdress("rue lal ");
        client2.setUsername("Magg");


        List<Client> list = new ArrayList<>();
        list.add(client1);
        list.add(client2);

        bank.setListClients(list);


    }

}
