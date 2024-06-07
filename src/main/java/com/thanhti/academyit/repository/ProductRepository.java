package com.thanhti.academyit.repository;

import com.thanhti.academyit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Using @Query annotation to define a custom query
    @Query(value = "SELECT * FROM Products ORDER BY price DESC LIMIT 5", nativeQuery = true)
    List<Product> findTop5Products();
}
