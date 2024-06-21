package com.thanhti.academyit.repository;

import com.thanhti.academyit.entity.CartItem;
import com.thanhti.academyit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem , Long> {

    @Query("SELECT c FROM CartItem c WHERE c.product.id = :productId AND c.account.email = :email")
    Optional<CartItem> findByProductIdAndEmail(@Param("productId") Long productId, @Param("email") String email);

    @Query("SELECT c FROM CartItem c WHERE  c.account.email = :email")
    List<CartItem> findByAccountEmail(@Param("email") String email);
}
