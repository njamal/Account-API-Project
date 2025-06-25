package com.example.accountapi.service;

import com.example.accountapi.model.AccountRecord;
import com.example.accountapi.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearch() {
        Page<AccountRecord> page = new PageImpl<>(List.of(new AccountRecord()));
        when(accountRepository.findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(
                anyString(), anyString(), anyString(), any(Pageable.class)))
                .thenReturn(page);

        Page<AccountRecord> result = accountService.search("CUST001", "ACC123", "Sample", PageRequest.of(0, 10));
        assertThat(result.getTotalElements()).isEqualTo(1);
    }

    @Test
    void testUpdateAccount_Success() {
        AccountRecord existing = new AccountRecord();
        existing.setId(1L);
        existing.setDescription("Old Description");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(accountRepository.save(any(AccountRecord.class))).thenReturn(existing);

        AccountRecord updated = accountService.updateAccount(1L, "New Description");

        assertThat(updated.getDescription()).isEqualTo("New Description");
    }

    @Test
    void testUpdateAccount_NotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> accountService.updateAccount(1L, "New Description"));
    }
}
