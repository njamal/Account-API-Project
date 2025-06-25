package com.example.accountapi.service;

import com.example.accountapi.model.AccountRecord;
import com.example.accountapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Page<AccountRecord> search(String customerId, String accountNumber, String description, Pageable pageable) {
        return accountRepository.findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(
                customerId, accountNumber, description, pageable);
    }

    @Transactional
    public AccountRecord updateAccount(Long id, String newDescription) {
        AccountRecord record = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setDescription(newDescription);
        return accountRepository.save(record); // Will auto-check version for optimistic locking
    }
}
