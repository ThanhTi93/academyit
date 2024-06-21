package com.thanhti.academyit.service.impl;

import com.thanhti.academyit.dto.OrderDTO;
import com.thanhti.academyit.entity.*;
import com.thanhti.academyit.repository.OrderDetailRepository;
import com.thanhti.academyit.repository.OrderRepository;
import com.thanhti.academyit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public Page<Order> findPaginated(Pageable pageable) {
        return orderRepository.findAll(pageable) ;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void createOrder(OrderDTO order, List<CartItem> cartItems) {
        // Convert OrderDTO to Order entity if needed
        Order orderEntity = new Order();
        orderEntity.setStatus(OrderStatus.UNPAID);
        orderEntity.setStatusCheckout(StatusCheckOut.DELIVERED);
        orderEntity.setOrderDate(new Date());
        orderEntity.setFreight(Double.valueOf("20"));
        orderEntity.setName(order.getName());
        orderEntity.setShipAddress(order.getShipAddress());
        orderEntity.setPhone(order.getPhone());
        // Save the order
        orderRepository.save(orderEntity);

        // Convert CartItems to OrderDetails and save them
        for (CartItem cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setUnitPrice(cartItem.getProduct().getPrice());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setOrder(orderEntity);

            orderDetailRepository.save(orderDetail);
        }
    }
}
