package com.thanhti.academyit.service;

import com.thanhti.academyit.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetail> findByOrderId(Long orderId);
}
