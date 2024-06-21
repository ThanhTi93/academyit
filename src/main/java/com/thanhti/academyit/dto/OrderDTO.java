package com.thanhti.academyit.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {

    private Long orderId;
    private String name;
    private Date orderDate;
    private Double freight;
    private String shipAddress;
    private String paymentMethod;
    private boolean statusCheckout;
    private String phone;
    private double tongTien;

}
