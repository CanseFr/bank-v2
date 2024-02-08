package org.bank.bankv2;

import org.bank.bankv2.models.Bank;
import org.bank.bankv2.models.Client;
import org.bank.bankv2.repositories.BankRepository;
import org.bank.bankv2.services.Impl.BankServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BankServiceImplTest {

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankServiceImpl bankService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBank() {
        Bank bank = new Bank();
        bank.setId(1);
        when(bankRepository.save(bank)).thenReturn(bank);

        Integer savedId = bankService.save(bank);

        assertEquals(bank.getId(), savedId);
        verify(bankRepository, times(1)).save(bank);
    }

    @Test
    void findAllBanks() {
        List<Bank> banks = new ArrayList<>();
        banks.add(new Bank());
        when(bankRepository.findAll()).thenReturn(banks);

        List<Bank> foundBanks = bankService.findAll();

        assertEquals(banks, foundBanks);
        verify(bankRepository, times(1)).findAll();
    }

    @Test
    void findBankById() {
        Bank bank = new Bank();
        bank.setId(1);
        when(bankRepository.findById(1)).thenReturn(Optional.of(bank));

        Bank foundBank = bankService.findById(1);

        assertEquals(bank, foundBank);
        verify(bankRepository, times(1)).findById(1);
    }

    @Test
    void deleteBankById() {
        bankService.delete(1);

        verify(bankRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetAllClientByBankId() {
        // Given
        Integer bankId = 1;
        Bank bank = new Bank();
        bank.setId(bankId);

        Client client1 = new Client();
        client1.setId(1);
        client1.setUsername("Client1");
        client1.setAdress("Address1");
        client1.setBank(bank);

        Client client2 = new Client();
        client2.setId(2);
        client2.setUsername("Client2");
        client2.setAdress("Address2");
        client2.setBank(bank);

        List<Client> expectedClients = Arrays.asList(client1, client2);

        // Mocking repository behavior
        when(bankRepository.findAllClientByBankId(bankId)).thenReturn(expectedClients);

        // When
        List<Client> actualClients = bankService.getAllClientByBankId(bankId);

        // Then
        assertEquals(expectedClients.size(), actualClients.size());
        for (int i = 0; i < expectedClients.size(); i++) {
            assertEquals(expectedClients.get(i).getId(), actualClients.get(i).getId());
            assertEquals(expectedClients.get(i).getUsername(), actualClients.get(i).getUsername());
            assertEquals(expectedClients.get(i).getAdress(), actualClients.get(i).getAdress());
            assertEquals(expectedClients.get(i).getBank(), actualClients.get(i).getBank());
        }
    }
}
