package org.bank.bankv2;

import org.bank.bankv2.controllers.ClientController;
import org.bank.bankv2.models.Client;
import org.bank.bankv2.services.ClientService;
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

public class ClientTest {
    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Client> expectedClients = Arrays.asList(new Client(), new Client());
        when(clientService.findAll()).thenReturn(expectedClients);

        ResponseEntity<List<Client>> response = clientController.findAll();

        assertEquals(expectedClients, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindById() {
        Integer clientId = 1;
        Client expectedClient = new Client();
        when(clientService.findById(clientId)).thenReturn(expectedClient);

        ResponseEntity<Client> response = clientController.findById(clientId);

        assertEquals(expectedClient, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSave() {
        Client clientToSave = new Client();
        Integer savedClientId = 1;
        when(clientService.save(clientToSave)).thenReturn(savedClientId);

        ResponseEntity<Integer> response = clientController.save(clientToSave);

        assertEquals(savedClientId, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDelete() {
        Integer clientId = 1;

        ResponseEntity<Void> response = clientController.delete(clientId);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(clientService, times(1)).delete(clientId);
    }
}
