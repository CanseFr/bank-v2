package org.bank.bankv2.unit;

import org.bank.bankv2.controllers.NoOverAccountController;
import org.bank.bankv2.models.NoOverAccount;
import org.bank.bankv2.services.NoOverAccountService;
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

public class NoOverAccountTest {
    @Mock
    private NoOverAccountService noOverAccountService;

    @InjectMocks
    private NoOverAccountController noOverAccountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<NoOverAccount> expectedAccounts = Arrays.asList(new NoOverAccount(), new NoOverAccount());
        when(noOverAccountService.findAll()).thenReturn(expectedAccounts);

        ResponseEntity<List<NoOverAccount>> response = noOverAccountController.findAll();

        assertEquals(expectedAccounts, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindById() {
        Integer accountId = 1;
        NoOverAccount expectedAccount = new NoOverAccount();
        when(noOverAccountService.findById(accountId)).thenReturn(expectedAccount);

        ResponseEntity<NoOverAccount> response = noOverAccountController.findById(accountId);

        assertEquals(expectedAccount, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSave() {
        NoOverAccount accountToSave = new NoOverAccount();
        Integer savedAccountId = 1;
        when(noOverAccountService.save(accountToSave)).thenReturn(savedAccountId);

        ResponseEntity<Integer> response = noOverAccountController.save(accountToSave);

        assertEquals(savedAccountId, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDelete() {
        Integer accountId = 1;

        ResponseEntity<Void> response = noOverAccountController.delete(accountId);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(noOverAccountService, times(1)).delete(accountId);
    }
    @Test
    public void testDebitByUserId() {
        Integer amount = 500;
        Integer userId = 1;
        String expectedResponse = "OK";
        when(noOverAccountService.debitByUserId(amount, userId)).thenReturn(expectedResponse);

        ResponseEntity<String> response = noOverAccountController.debitByUserId(amount, userId);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testCreditByUserId() {
        Integer amount = 500;
        Integer userId = 1;
        String expectedResponse = "OK";
        when(noOverAccountService.creditByUserId(amount, userId)).thenReturn(expectedResponse);

        ResponseEntity<String> response = noOverAccountController.creditByUserId(amount, userId);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}