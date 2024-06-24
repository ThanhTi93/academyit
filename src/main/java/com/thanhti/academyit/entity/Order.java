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

    @Column( nullable = true)
    private String phone;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(nullable = false)
    private Double freight;

    @Column(columnDefinition = "varchar(100) not null")
    private String shipAddress;

    @Column(columnDefinition = "varchar(200)")
    private String name;

    @Enumerated(EnumType.STRING)  // Sử dụng EnumType.STRING để lưu giá trị enum dưới dạng chuỗi
    @Column(nullable = false)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCheckOut statusCheckout;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "email")
    private  Account account;

}

