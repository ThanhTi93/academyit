package com.thanhti.academyit.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

    private Double unitPrice;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}
