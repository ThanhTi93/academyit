package com.thanhti.academyit.service;

import com.thanhti.academyit.entity.Category;
import com.thanhti.academyit.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    <S extends Category> S save(S entity);

    Page<Category> findPaginated(Pageable pageable);

    List<Category> findAll();

    Page<Category> findByCategoryNameContaining(String name, Pageable pageable);

    Optional<Category> findById(Long id);

    void deleteById(Long id);
}
