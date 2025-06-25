package com.example.accountapi.repository;

import com.example.accountapi.model.AccountRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountRecord, Long> {

    Page<AccountRecord> findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(
            String customerId, String accountNumber, String description, Pageable pageable);

    Page<AccountRecord> findByCustomerIdContainingIgnoreCaseOrAccountNumberContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String customerId, String accountNumber, String description, Pageable pageable);

    Optional<AccountRecord> findByCustomerId(String customerId);
}
