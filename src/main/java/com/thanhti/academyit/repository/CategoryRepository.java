package com.thanhti.academyit.repository;

import com.thanhti.academyit.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {

    Page<Category> findByCategoryNameContaining(String name, Pageable pageable);
}
