package com.example.accountapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner loadFile(FileBatchService fileBatchService) {
//		return args -> {
//			System.out.println("Batch loader started...");
//			String filePath = "C:/accountapi/data/accounts.txt"; // Change this to your actual file path
//			fileBatchService.processFile(filePath);
//			System.out.println("Batch loader completed.");
//		};
//	}
}
