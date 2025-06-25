package com.example.accountapi.service;

import com.example.accountapi.model.AccountRecord;
import com.example.accountapi.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    private AccountRepository accountRepository;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
    }

    @Test
    void testUpdateDescription_Success() {
        // Given
        Long recordId = 1L;
        String newDescription = "Updated Description";
        AccountRecord existingRecord = new AccountRecord();
        existingRecord.setId(recordId);
        existingRecord.setDescription("Old Description");

        // When
        when(accountRepository.findById(recordId)).thenReturn(Optional.of(existingRecord));
        when(accountRepository.save(existingRecord)).thenReturn(existingRecord);

        // Action
        AccountRecord updatedRecord = accountService.updateDescription(recordId, newDescription);

        // Then
        assertNotNull(updatedRecord);
        assertEquals(newDescription, updatedRecord.getDescription());
        verify(accountRepository, times(1)).findById(recordId);
        verify(accountRepository, times(1)).save(existingRecord);
    }

    @Test
    void testUpdateDescription_RecordNotFound() {
        // Given
        Long recordId = 99L;
        String newDescription = "Description";

        // When
        when(accountRepository.findById(recordId)).thenReturn(Optional.empty());

        // Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accountService.updateDescription(recordId, newDescription);
        });

        assertEquals("Account not found with id: " + recordId, exception.getMessage());
        verify(accountRepository, times(1)).findById(recordId);
        verify(accountRepository, times(0)).save(any());
    }
}
