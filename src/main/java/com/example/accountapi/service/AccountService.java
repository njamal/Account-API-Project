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

//    public Page<AccountRecord> search(String customerId, String accountNumber, String description, Pageable pageable) {
//        return accountRepository.findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(
//                customerId, accountNumber, description, pageable);
//    }

    public Page<AccountRecord> search(String customerId, String accountNumber, String description, Pageable pageable) {
        boolean isCustomerIdEmpty = (customerId == null || customerId.isEmpty());
        boolean isAccountNumberEmpty = (accountNumber == null || accountNumber.isEmpty());
        boolean isDescriptionEmpty = (description == null || description.isEmpty());

        // If all search fields are empty → return all records
        if (isCustomerIdEmpty && isAccountNumberEmpty && isDescriptionEmpty) {
            return accountRepository.findAll(pageable);
        }

        return accountRepository.findByCustomerIdContainingIgnoreCaseOrAccountNumberContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                isCustomerIdEmpty ? "" : customerId,
                isAccountNumberEmpty ? "" : accountNumber,
                isDescriptionEmpty ? "" : description,
                pageable);
    }

    @Transactional
    public AccountRecord updateAccount(Long id, String newDescription) {
        AccountRecord record = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setDescription(newDescription);
        return accountRepository.save(record); // Will auto-check version for optimistic locking
    }

    @Transactional
    public AccountRecord updateDescription(Long id, String newDescription) {
        AccountRecord record = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        record.setDescription(newDescription);

        return accountRepository.save(record); // Will auto-check the version field for concurrency
    }

    @Transactional
    public AccountRecord updateDescriptionByCustomerId(String customerId, String newDescription) {
        AccountRecord record = accountRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Account not found with customerId: " + customerId));

        record.setDescription(newDescription);

        return accountRepository.save(record);
    }
}
