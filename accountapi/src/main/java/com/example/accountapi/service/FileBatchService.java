package com.example.accountapi.service;

import com.example.accountapi.model.AccountRecord;
import com.example.accountapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileBatchService {

    private final AccountRepository accountRepository;

    public void processFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    AccountRecord record = new AccountRecord();
                    record.setCustomerId(parts[0].trim());
                    record.setAccountNumber(parts[1].trim());
                    record.setDescription(parts[2].trim());
                    accountRepository.save(record);
                }
            }
        }
    }
}