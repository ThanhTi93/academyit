package com.thanhti.academyit.service.impl;

import com.thanhti.academyit.entity.Product;
import com.thanhti.academyit.repository.ProductRepository;
import com.thanhti.academyit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository ;

    @Value("${upload.path}")
    private String uploadPath; // Đường dẫn đến thư mục images, được cấu hình trong application.properties

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    public String saveImage(MultipartFile imageFile) throws IOException {
        // Tạo đường dẫn đến thư mục images
        Path uploadDir = Paths.get(uploadPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Lưu ảnh vào thư mục static/images
        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        Path filePath = uploadDir.resolve(fileName);
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Trả về đường dẫn của ảnh đã lưu
        return "/images/" + fileName; // Đường dẫn tương đối tới thư mục images
    }


    @Override
    public Page<Product> findPaginated(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllMobile() {
        return productRepository.findAllMobile();
    }
}
