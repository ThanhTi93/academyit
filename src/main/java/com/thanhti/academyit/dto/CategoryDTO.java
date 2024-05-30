package com.thanhti.academyit.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CategoryDTO {

    private Long categoryId;

    @NotEmpty(message = "Category name cannot be empty")
    @Length(min = 5, message = "Category name must be at least 5 characters long")
    private String categoryName;

    @NotEmpty(message = "Description cannot be empty")
    @Length(min = 20, message = "Description must be at least 20 characters long")
    private String description;

    private Boolean isEdit = false ;
}
