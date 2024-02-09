package org.bank.bankv2;

import org.bank.bankv2.controllers.OverAccountController;
import org.bank.bankv2.models.OverAccount;
import org.bank.bankv2.services.OverAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Unit_OverAccountTest {
    @Mock
    private OverAccountService overAccountService;

    @InjectMocks
    private OverAccountController overAccountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<OverAccount> expectedAccounts = Arrays.asList(new OverAccount(), new OverAccount());
        when(overAccountService.findAll()).thenReturn(expectedAccounts);

        ResponseEntity<List<OverAccount>> response = overAccountController.findAll();

        assertEquals(expectedAccounts, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindById() {
        Integer accountId = 1;
        OverAccount expectedAccount = new OverAccount();
        when(overAccountService.findById(accountId)).thenReturn(expectedAccount);

        ResponseEntity<OverAccount> response = overAccountController.findById(accountId);

        assertEquals(expectedAccount, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSave() {
        OverAccount accountToSave = new OverAccount();
        Integer savedAccountId = 1;
        when(overAccountService.save(accountToSave)).thenReturn(savedAccountId);

        ResponseEntity<Integer> response = overAccountController.save(accountToSave);

        assertEquals(savedAccountId, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDelete() {
        Integer accountId = 1;

        ResponseEntity<Void> response = overAccountController.delete(accountId);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(overAccountService, times(1)).delete(accountId);
    }

    @Test
    public void testDebitByUserId() {
        Integer amount = 500;
        Integer userId = 1;
        String expectedResponse = "OK";
        when(overAccountService.debitByUserId(amount, userId)).thenReturn(expectedResponse);

        ResponseEntity<String> response = overAccountController.debitByUserId(amount, userId);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreditByUserId() {
        Integer amount = 500;
        Integer userId = 1;
        String expectedResponse = "OK";
        when(overAccountService.creditByUserId(amount, userId)).thenReturn(expectedResponse);

        ResponseEntity<String> response = overAccountController.creditByUserId(amount, userId);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
