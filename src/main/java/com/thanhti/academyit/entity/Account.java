package com.thanhti.academyit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "user"; // default value is 'user'

    // Relationships
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Customer customer;

    @Column(name = "confirmation_token")
    private String confirmationToken; // Add confirmationToken field

    @Column(name = "enabled")
    private boolean enabled;
}
