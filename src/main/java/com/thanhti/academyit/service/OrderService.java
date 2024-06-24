package com.thanhti.academyit.service;

import com.thanhti.academyit.dto.OrderDTO;
import com.thanhti.academyit.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Page<Order> findPaginated(Pageable pageable);

    Optional<Order> findById(Long id);

    <S extends Order> S save(S entity);

    void createOrder(OrderDTO order, List<CartItem> cartItems, Account account);
}
