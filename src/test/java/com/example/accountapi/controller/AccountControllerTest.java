package com.example.accountapi.controller;

import com.example.accountapi.service.AccountService;
import com.example.accountapi.service.FileBatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@Import(AccountControllerTest.MockConfig.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FileBatchService fileBatchService;

    @BeforeEach
    void setUp() {
        // Optional: You can set default mock behavior here if needed
    }

    @Test
    void testFileUpload_Success() throws Exception {
        // Prepare a mock file
        MockMultipartFile file = new MockMultipartFile(
                "file",                          // Key name in form-data
                "accounts.txt",                  // File name
                MediaType.TEXT_PLAIN_VALUE,      // File type
                "ACC12345\tCUST001\tFirst Account".getBytes() // File content
        );

        // Mock service to do nothing
        doNothing().when(fileBatchService).processFile(any());

        // Perform upload
        mockMvc.perform(multipart("/api/accounts/upload").file(file))
                .andExpect(status().isOk());

        // Verify the service was called
        verify(fileBatchService, times(1)).processFile(any());
    }

    // ✅ Optional: Test error case
    @Test
    void testFileUpload_Failure() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "accounts.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "ACC12345\tCUST001\tFirst Account".getBytes()
        );

        // Simulate an exception when processing the file
        doThrow(new IOException("File processing error")).when(fileBatchService).processFile(any());

        mockMvc.perform(multipart("/api/accounts/upload").file(file))
                .andExpect(status().isInternalServerError());

        verify(fileBatchService, times(1)).processFile(any());
    }

    // ✅ Mock Configuration
    static class MockConfig {
        @Bean
        public AccountService accountService() {
            return mock(AccountService.class);
        }

        @Bean
        public FileBatchService fileBatchService() {
            return mock(FileBatchService.class);
        }
    }
}
