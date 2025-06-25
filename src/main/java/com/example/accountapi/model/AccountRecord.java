package com.example.accountapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "account_record")
@Data
public class AccountRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String description;

    @Version
    private Integer version; // For Optimistic Locking
}