package com.example.accountapi.controller;

import com.example.accountapi.model.AccountRecord;
import com.example.accountapi.service.AccountService;
import com.example.accountapi.service.FileBatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final FileBatchService fileBatchService;

    @GetMapping
    public Page<AccountRecord> search(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String accountNumber,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return accountService.search(
                customerId != null ? customerId : "",
                accountNumber != null ? accountNumber : "",
                description != null ? description : "",
                PageRequest.of(page, size));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<AccountRecord> updateDescription(
//            @PathVariable Long id,
//            @RequestParam String description) {
//
//        AccountRecord updatedRecord = accountService.updateDescription(id, description);
//        return ResponseEntity.ok(updatedRecord);
//    }

    @PutMapping("/{id}")
    @Operation(summary = "Update account description", description = "Updates the description of an account by ID with version control.")
    public ResponseEntity<AccountRecord> updateDescription(
            @Parameter(description = "Account ID to update") @PathVariable Long id,
            @Parameter(description = "New description to set") @RequestParam String description) {

        AccountRecord updatedRecord = accountService.updateDescription(id, description);
        return ResponseEntity.ok(updatedRecord);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileBatchService.processFile(file);
            return ResponseEntity.ok("File uploaded and processed successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            // Log the error for debugging
            System.err.println("File processing failed: " + e.getMessage());
            return ResponseEntity.status(500).body("File processing failed: " + e.getMessage());
        }
    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            fileBatchService.processFile(file);
//            return ResponseEntity.ok("File uploaded and processed successfully: " + file.getOriginalFilename());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("File processing failed: " + e.getMessage());
//        }
//    }

//    @PutMapping("/customer/{customerId}")
//    public ResponseEntity<AccountRecord> updateByCustomerId(
//            @PathVariable String customerId,
//            @RequestParam String description) {
//
//        AccountRecord updatedRecord = accountService.updateDescriptionByCustomerId(customerId, description);
//        return ResponseEntity.ok(updatedRecord);
//    }

//    @PutMapping("/{id}")
//    public AccountRecord updateAccount(@PathVariable Long id, @RequestParam String description) {
//        return accountService.updateAccount(id, description);
//    }
}
