package com.example.accountapi.controller;

import com.example.accountapi.model.AccountRecord;
import com.example.accountapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public Page<AccountRecord> search(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String accountNumber,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return accountService.search(customerId, accountNumber, description, PageRequest.of(page, size));
    }

    @PutMapping("/{id}")
    public AccountRecord updateDescription(@PathVariable Long id, @RequestParam String description) {
        return accountService.updateAccount(id, description);
    }
}
