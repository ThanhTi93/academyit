package com.thanhti.academyit.controller;

import ch.qos.logback.core.model.Model;
import com.thanhti.academyit.dto.CategoryDTO;
import com.thanhti.academyit.entity.Category;
import com.thanhti.academyit.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("")
    public String showCategories(ModelMap model,
                                 @RequestParam(name = "name", required = false) String name,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage;

        if (StringUtils.hasText(name)) {
            categoryPage = categoryService.findByCategoryNameContaining(name, pageable);
        } else {
            categoryPage = categoryService.findPaginated(pageable);
        }

        model.addAttribute("page", categoryPage);
        return "admin/categories/Categories";
    }

    @GetMapping("add")
    public String add(ModelMap model) {

        model.addAttribute("category", new CategoryDTO());
        return"admin/categories/AddOrEdit";
    }

    @PostMapping("/saveOrUpdate")
    public ModelAndView addCategory(@Valid @ModelAttribute("category") CategoryDTO dto, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/categories/AddOrEdit", model);
        }
        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);
        categoryService.save(entity);
        model.addAttribute("message", "Category is saved!");
        return new ModelAndView("forward:/categories", model);
    }
}
