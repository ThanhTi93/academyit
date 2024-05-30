package com.thanhti.academyit.service;

import com.thanhti.academyit.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    <S extends Category> S save(S entity);

    Page<Category> findPaginated(Pageable pageable);

    List<Category> findAll();
}
