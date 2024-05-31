package com.thanhti.academyit.service.impl;

import com.thanhti.academyit.entity.Order;
import com.thanhti.academyit.repository.OrderRepository;
import com.thanhti.academyit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Page<Order> findPaginated(Pageable pageable) {
        return orderRepository.findAll(pageable) ;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
}
