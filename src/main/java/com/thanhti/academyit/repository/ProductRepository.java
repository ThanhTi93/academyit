package com.thanhti.academyit.repository;

import com.thanhti.academyit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Using @Query annotation to define a custom query
    @Query(value = "select * from products where products.category_id = 2", nativeQuery = true)
    List<Product> findAllMobile();
}
