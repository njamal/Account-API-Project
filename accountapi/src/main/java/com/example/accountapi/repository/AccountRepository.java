package com.example.accountapi.repository;

import com.example.accountapi.model.AccountRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountRecord, Long> {

    Page<AccountRecord> findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(
            String customerId, String accountNumber, String description, Pageable pageable);
}
