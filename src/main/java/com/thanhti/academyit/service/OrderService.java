package com.thanhti.academyit.service;

import com.thanhti.academyit.entity.Order;
import com.thanhti.academyit.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderService {

    Page<Order> findPaginated(Pageable pageable);

    Optional<Order> findById(Long id);
}
