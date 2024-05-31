package com.thanhti.academyit.repository;

import com.thanhti.academyit.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("Select od From OrderDetail od where od.order.orderId = ?1")
    List<OrderDetail> findByOrderId(Long orderId);
}
