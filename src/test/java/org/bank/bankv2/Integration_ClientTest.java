package org.bank.bankv2;

import org.bank.bankv2.models.Client;
import org.bank.bankv2.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class Integration_ClientTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testSaveClient() throws Exception {
        String requestBody = "{\"username\":\"testuser\",\"adress\":\"testadress\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/client/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<Client> savedClients = clientRepository.searchByFirstName("testuser");
        assert !savedClients.isEmpty();
        Client savedClient = savedClients.get(0);
        assert savedClient.getUsername().equals("testuser");
        assert savedClient.getAdress().equals("testadress");
    }

    @Test
    public void testFindAllClients() throws Exception {
        Client client1 = new Client(null, "user1", "address1", null, null);
        Client client2 = new Client(null, "user2", "address2", null, null);
        clientRepository.saveAll(List.of(client1, client2));

        mockMvc.perform(MockMvcRequestBuilders.get("/client/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("user1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").value("user2"));
    }

    @Test
    public void testFindByIdWithInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/client/client-id/{id}", 999))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
