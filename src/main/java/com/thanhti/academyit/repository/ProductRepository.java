package com.thanhti.academyit.repository;

import com.thanhti.academyit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Using @Query annotation to define a custom query
    @Query(value = "select * from products where products.category_id = 2", nativeQuery = true)
    List<Product> findAllMobile();

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.cartItemList WHERE p.id = :id")
    Optional<Product> findByIdWithCartItems(@Param("id") Long id);
}
