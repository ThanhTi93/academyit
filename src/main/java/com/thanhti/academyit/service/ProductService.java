package com.thanhti.academyit.service;

import com.thanhti.academyit.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    <S extends Product> S save(S entity);

    String saveImage(MultipartFile imageFile) throws IOException;

    Page<Product> findPaginated(Pageable pageable);

    Optional<Product> findById(Long id);

    List<Product> findAllMobile();

    Optional<Product> findByIdWithCartItems(@Param("id") Long id);
}
