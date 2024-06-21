package com.thanhti.academyit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(unique = true, nullable = true)
    private String phone;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(length = 200)
    private String image;

    @OneToOne
    @JoinColumn(name = "email", nullable = false, referencedColumnName = "email")
    private Account account;

}
