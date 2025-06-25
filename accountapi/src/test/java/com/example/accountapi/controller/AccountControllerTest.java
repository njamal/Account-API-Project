package com.example.accountapi.controller;

import com.example.accountapi.model.AccountRecord;
import com.example.accountapi.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    void testSearch() throws Exception {
        Page<AccountRecord> page = new PageImpl<>(List.of(new AccountRecord()));
        Mockito.when(accountService.search(anyString(), anyString(), anyString(), any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(get("/api/accounts")
                        .param("customerId", "CUST001")
                        .param("accountNumber", "ACC123")
                        .param("description", "Sample")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateDescription() throws Exception {
        AccountRecord record = new AccountRecord();
        record.setId(1L);
        record.setDescription("Updated Description");

        Mockito.when(accountService.updateAccount(eq(1L), eq("Updated Description")))
                .thenReturn(record);

        mockMvc.perform(put("/api/accounts/1")
                        .param("description", "Updated Description"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }
}
