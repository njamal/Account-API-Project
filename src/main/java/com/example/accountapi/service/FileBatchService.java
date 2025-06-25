package com.example.accountapi.service;

import com.example.accountapi.model.AccountRecord;
import com.example.accountapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class FileBatchService {

    private final AccountRepository accountRepository;

    public void processFile(MultipartFile file) throws IOException {
        System.out.println("Starting file processing: " + file.getOriginalFilename());

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line);
                String[] parts = line.split("\t");

                if (parts.length == 3) {
                    AccountRecord record = new AccountRecord();
                    record.setAccountNumber(parts[0].trim());
                    record.setCustomerId(parts[1].trim());
                    record.setDescription(parts[2].trim());

                    accountRepository.save(record);
                    count++;
                    System.out.println("Record saved: " + record);
                } else {
                    System.out.println("Invalid line skipped: " + line);
                }
            }

            System.out.println("File processing completed. Total records saved: " + count);
        }
    }

//    public void processFile(String filePath) throws IOException {
//        System.out.println("Starting file processing: " + filePath);
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            int count = 0;
//
//            while ((line = br.readLine()) != null) {
//                System.out.println("Reading line: " + line);
//
//                // Correct splitting by tab
//                String[] parts = line.split("\t");
//
//                if (parts.length == 3) {
//                    AccountRecord record = new AccountRecord();
//                    record.setAccountNumber(parts[0].trim());
//                    record.setCustomerId(parts[1].trim());
//                    record.setDescription(parts[2].trim());
//
//                    accountRepository.save(record);
//                    count++;
//                    System.out.println("Record saved: " + record);
//                } else {
//                    System.out.println("Invalid line skipped: " + line);
//                }
//            }
//
//            System.out.println("File processing completed. Total records saved: " + count);
//        }
//    }

}