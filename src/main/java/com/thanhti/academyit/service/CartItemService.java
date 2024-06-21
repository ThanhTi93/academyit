package com.thanhti.academyit.service;

import com.thanhti.academyit.entity.CartItem;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemService {

    <S extends CartItem> S save(S entity);

    Optional<CartItem> findByProductIdAndEmail(@Param("productId") Long productId, @Param("email") String email);

    List<CartItem> findByAccountEmail(@Param("email") String email);

    void delete(CartItem entity);
}
