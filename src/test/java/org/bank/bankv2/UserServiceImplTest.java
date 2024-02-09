package org.bank.bankv2;

import org.bank.bankv2.models.Client;
import org.bank.bankv2.repositories.ClientRepository;
import org.bank.bankv2.services.Impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private ClientRepository userRepository;

    @InjectMocks
    private ClientServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveClient() {
        Client client = new Client();
        client.setId(1);
        when(userRepository.save(client)).thenReturn(client);

        Integer savedId = userService.save(client);

        assertEquals(client.getId(), savedId);
        verify(userRepository, times(1)).save(client);
    }

    @Test
    void findClientById() {
        Client client = new Client();
        client.setId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(client));

        Client foundClient = userService.findById(1);

        assertEquals(client, foundClient);
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void deleteClientById() {
        userService.delete(1);

        verify(userRepository, times(1)).deleteById(1);
    }
}
