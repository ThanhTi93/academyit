package com.thanhti.academyit.dto;

import com.thanhti.academyit.entity.OrderStatus;
import com.thanhti.academyit.entity.StatusCheckOut;
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
    private StatusCheckOut statusCheckout; // Sử dụng enum StatusCheckOut thay vì boolean
    private String phone;
    private double tongTien;
    private OrderStatus status;

}
