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

import java.util.Optional;

@Controller
@RequestMapping("admin/categories")
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
        return new ModelAndView("forward:/admin/categories", model);
    }

    @GetMapping("/edit/{categoryId}")
    public  ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long id) {

        Optional<Category> opt = categoryService.findById(id);
        CategoryDTO dto = new CategoryDTO();

        if(opt.isPresent()) {
            Category entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);
            model.addAttribute("category", dto);
            return new ModelAndView("admin/categories/AddOrEdit", model);
        }
        model.addAttribute("message", "Category is not existed");

        return new ModelAndView("forward:/admin/categories", model);
    }

    @GetMapping("delete/{categoryId}")
    public  ModelAndView deleted(ModelMap model,@PathVariable("categoryId") Long id ) {

           categoryService.deleteById(id);
        model.addAttribute("message", "Category is deleted !");

        return new ModelAndView("forward:/admin/categories", model);
    }
}
