package com.thanhti.academyit.service.impl;

import com.thanhti.academyit.entity.Order;
import com.thanhti.academyit.entity.OrderDetail;
import com.thanhti.academyit.repository.OrderDetailRepository;
import com.thanhti.academyit.service.OrderDetailService;
import com.thanhti.academyit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository ;

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return  orderDetailRepository.findByOrderId(orderId);
    }
}
