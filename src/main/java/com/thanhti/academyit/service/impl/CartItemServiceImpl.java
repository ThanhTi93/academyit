package com.thanhti.academyit.service.impl;

import com.thanhti.academyit.entity.CartItem;
import com.thanhti.academyit.repository.CartItemRepository;
import com.thanhti.academyit.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;


    @Override
    public <S extends CartItem> S save(S entity) {
        return cartItemRepository.save(entity);
    }

    @Override
    public Optional<CartItem> findByProductIdAndEmail(Long productId, String email) {
        return cartItemRepository.findByProductIdAndEmail(productId, email);
    }

    @Override
    public List<CartItem> findByAccountEmail(String email) {
        return cartItemRepository.findByAccountEmail(email);
    }

    @Override
    public void delete(CartItem entity) {
            cartItemRepository.delete(entity);
    }


}
