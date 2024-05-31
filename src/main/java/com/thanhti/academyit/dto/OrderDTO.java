package com.thanhti.academyit.dto;

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
    private String note;
    private Date orderDate;
    private Double freight;
    private String shipAddress;
    private short status;
    private boolean statusCheckout;

    private double tongTien;

}
