package com.thanhti.academyit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(columnDefinition = "varchar(100) not null")
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    @Column(length = 200)
    private String image;

    @Column(nullable = false)
    private double discount;

    @Column(columnDefinition = "varchar(300) not null")
    private String description;

    @Temporal(TemporalType.DATE)
    private Date enteredDate;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

}
