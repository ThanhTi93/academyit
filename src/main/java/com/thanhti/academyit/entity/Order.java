package com.thanhti.academyit.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(nullable = false)
    private Double freight;

    @Column(columnDefinition = "varchar(100) not null")
    private String shipAddress;

    @Column(columnDefinition = "varchar(200)")
    private String note;

    @Column(nullable = false)
    private short status;

    @Column(nullable = false)
    private boolean statusCheckout;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}

