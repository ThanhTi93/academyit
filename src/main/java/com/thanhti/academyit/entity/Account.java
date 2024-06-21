package com.thanhti.academyit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(nullable = false, unique = true)
    private String email;  // Use email as a natural ID without @GeneratedValue

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)  // Sử dụng EnumType.STRING để lưu giá trị enum dưới dạng chuỗi
    @Column(nullable = false)
    private UserRole role;

    // Relationships
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Customer customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<CartItem> cartItemList;

    @OneToMany(mappedBy = "account")
    private  List<Order> orderList ;

}
