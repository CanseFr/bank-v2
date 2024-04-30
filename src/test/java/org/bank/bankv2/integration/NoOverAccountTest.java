package org.bank.bankv2.integration;

import org.bank.bankv2.services.NoOverAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class NoOverAccountTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoOverAccountService noOverAccountService;

    @Test
    public void testFindByIdWithInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/no-over-account/no-over-account-id/{id}", 999))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
