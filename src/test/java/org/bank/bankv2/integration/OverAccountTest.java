package org.bank.bankv2.integration;

import org.bank.bankv2.services.OverAccountService;
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
public class OverAccountTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OverAccountService overAccountService;

    @Test
    public void testFindByIdWithInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/over-account/over-account-id/{id}", 999))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
