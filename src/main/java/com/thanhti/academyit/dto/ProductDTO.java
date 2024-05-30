package com.thanhti.academyit.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Long productId;

    @NotEmpty(message = "Category name cannot be empty")
    @Length(min = 5, message = "Category name must be at least 5 characters long")
    private String name;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;

    private double price;

    private String image;

    private Date enteredDate;

    private MultipartFile imageFile;

    @NotEmpty(message = "Description cannot be empty")
    @Length(min = 20, message = "Description must be at least 20 characters long")
    private String description;

    private double discount;

    @NotNull(message = "Category ID must be selected")
    private Long categoryId;

    private Boolean isEdit = false ;
}
