package org.bank.bankv2;

import org.bank.bankv2.classes.Currencies;
import org.bank.bankv2.enums.Currency;
import org.bank.bankv2.models.*;
import org.bank.bankv2.repositories.BankRepository;
import org.bank.bankv2.repositories.NoOverAccountRepository;
import org.bank.bankv2.repositories.OverAccountRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

class Unit_BankServiceImplTest {

    @Mock
    private BankRepository bankRepository;

    @Mock
    private OverAccountRepository overAccountRepository;

    @Mock
    private NoOverAccountRepository noOverAccountRepository;

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

    @Test
    void debitByAccountId_OverAccount() {
        // Given
        Integer accountId = 1;
        Float debitAmount = 100.0f;
        OverAccount overAccount = new OverAccount();
        overAccount.setId(accountId);
        overAccount.setSolde(200.0f);

        when(overAccountRepository.findById(accountId)).thenReturn(Optional.of(overAccount));
        when(overAccountRepository.save(any(OverAccount.class))).then(returnsFirstArg());

        // When
        Account result = bankService.debitByAccountId(accountId, debitAmount);

        // Then
        assertNotNull(result);
        assertInstanceOf(OverAccount.class, result);
        assertEquals(100.0f, result.getSolde()); // 200 - 100 = 100
        verify(overAccountRepository).save(any(OverAccount.class));
    }

    @Test
    void debitByAccountId_NoOverAccount() {
        // Given
        Integer accountId = 2;
        Float debitAmount = 50.0f;
        NoOverAccount noOverAccount = new NoOverAccount();
        noOverAccount.setId(accountId);
        noOverAccount.setSolde(200.0f); // suppose that the account initially has 200

        when(noOverAccountRepository.findById(accountId)).thenReturn(Optional.of(noOverAccount));
        when(noOverAccountRepository.save(any(NoOverAccount.class))).then(returnsFirstArg());

        // When
        Account result = bankService.debitByAccountId(accountId, debitAmount);

        // Then
        assertNotNull(result);
        assertInstanceOf(NoOverAccount.class, result);
        assertEquals(150.0f, result.getSolde()); // 200 - 50 = 150
        verify(noOverAccountRepository).save(any(NoOverAccount.class));
    }

    @Test
    void creditByAccountId_OverAccount() {
        // Given
        Integer accountId = 1;
        Float creditAmount = 100.0f;
        OverAccount overAccount = new OverAccount();
        overAccount.setId(accountId);
        overAccount.setSolde(200.0f); // suppose that the account initially has 200

        when(overAccountRepository.findById(accountId)).thenReturn(Optional.of(overAccount));
        when(overAccountRepository.save(any(OverAccount.class))).then(returnsFirstArg());

        // When
        Account result = bankService.creditByAccountId(accountId, creditAmount);

        // Then
        assertNotNull(result);
        assertInstanceOf(OverAccount.class, result);
        assertEquals(300.0f, result.getSolde()); // 200 + 100 = 300
        verify(overAccountRepository).save(any(OverAccount.class));
    }

    @Test
    void creditByAccountId_NoOverAccount() {
        // Given
        Integer accountId = 2;
        Float creditAmount = 100.0f;
        NoOverAccount noOverAccount = new NoOverAccount();
        noOverAccount.setId(accountId);
        noOverAccount.setSolde(150.0f); // suppose that the account initially has 150

        when(noOverAccountRepository.findById(accountId)).thenReturn(Optional.of(noOverAccount));
        when(noOverAccountRepository.save(any(NoOverAccount.class))).then(returnsFirstArg());

        // When
        Account result = bankService.creditByAccountId(accountId, creditAmount);

        // Then
        assertNotNull(result);
        assertInstanceOf(NoOverAccount.class, result);
        assertEquals(250.0f, result.getSolde()); // 150 + 100 = 250
        verify(noOverAccountRepository).save(any(NoOverAccount.class));
    }

    @Test
    void createAccount_OverAccount() {
        // Given
        Integer clientId = 1;
        Integer bankId = 1;
        Integer over = 500; // positive value for OverAccount
        Bank bank = new Bank();
        bank.setId(bankId);

        OverAccount overAccount = new OverAccount();
        overAccount.setBank(bank);
        overAccount.setSolde(0.0f);
        overAccount.setOverdrawn(over.floatValue());

        when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));
        when(overAccountRepository.save(any(OverAccount.class))).thenReturn(overAccount);

        // When
        Account result = bankService.createAccount(clientId, bankId, over);

        // Then
        assertNotNull(result);
        assertInstanceOf(OverAccount.class, result);
        assertEquals(0.0f, result.getSolde());
        assertEquals(over.floatValue(), ((OverAccount) result).getOverdrawn());
        verify(overAccountRepository).save(any(OverAccount.class));
    }

    @Test
    void createAccount_NoOverAccount() {
        // Given
        Integer clientId = 1;
        Integer bankId = 1;
        Integer over = 0; // zero or negative value for NoOverAccount
        Bank bank = new Bank();
        bank.setId(bankId);

        NoOverAccount noOverAccount = new NoOverAccount();
        noOverAccount.setBank(bank);
        noOverAccount.setSolde(0.0f);

        when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));
        when(noOverAccountRepository.save(any(NoOverAccount.class))).thenReturn(noOverAccount);

        // When
        Account result = bankService.createAccount(clientId, bankId, over);

        // Then
        assertNotNull(result);
        assertInstanceOf(NoOverAccount.class, result);
        assertEquals(0.0f, result.getSolde());
        verify(noOverAccountRepository).save(any(NoOverAccount.class));
    }

    @Test
    void createAccount_BankNotFound() {
        // Given
        Integer clientId = 1;
        Integer bankId = 999; // non-existing bank
        Integer over = 0;

        when(bankRepository.findById(bankId)).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(RuntimeException.class, () -> bankService.createAccount(clientId, bankId, over));

        // Then
        assertNotNull(exception);
        verify(overAccountRepository, never()).save(any());
        verify(noOverAccountRepository, never()).save(any());
    }

    @Test
    void convertFromEuro_ToUSD() {
        // Given
        Float amount = 100.0f; // Amount in Euro
        String currency = "usd";
        Float expectedConversion = (float) (amount * Currency.USD.getRate());

        // When
        Currencies result = bankService.convertFromEuro(amount, currency);

        // Then
        assertNotNull(result);
        assertEquals(amount, result.getBaseAmountFrom());
        assertEquals(currency.toUpperCase(), result.getTo().toUpperCase());
        assertEquals(expectedConversion, result.getConvertedAmountTo());
    }

    @Test
    void convertFromEuro_UnsupportedCurrency() {
        // Given
        Float amount = 100.0f;
        String currency = "xyz"; // Assuming XYZ is not supported

        // When
        Currencies result = bankService.convertFromEuro(amount, currency);

        // Then
        assertNull(result.getConvertedAmountTo());
    }

    @Test
    void convertToEuro_FromUSD() {
        // Given
        Float amountInUSD = 100.0f; // Amount in USD
        String currencyFrom = "usd";
        Float expectedConversion = (float) (amountInUSD / Currency.USD.getRate()); // Use the rate from your Currency enum

        // When
        Currencies result = bankService.convertToEuro(amountInUSD, currencyFrom);

        // Then
        assertNotNull(result);
        assertEquals(amountInUSD, result.getBaseAmountFrom());
        assertEquals("EUR", result.getTo().toUpperCase()); // Assuming your method sets the "to" field to "EUR"
        assertEquals(expectedConversion, result.getConvertedAmountTo());
    }

    @Test
    void convertToEuro_UnsupportedCurrency() {
        // Given
        Float amount = 100.0f;
        String currencyFrom = "xyz"; // Assuming XYZ is not supported

        // When
        Currencies result = bankService.convertToEuro(amount, currencyFrom);

        // Then
        assertNull(result.getConvertedAmountTo());
    }
}
