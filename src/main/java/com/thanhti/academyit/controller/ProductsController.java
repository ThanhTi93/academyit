package com.thanhti.academyit.controller;
import com.thanhti.academyit.dto.CategoryDTO;
import com.thanhti.academyit.dto.ProductDTO;
import com.thanhti.academyit.entity.Category;
import com.thanhti.academyit.entity.Product;
import com.thanhti.academyit.service.CategoryService;
import com.thanhti.academyit.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @ModelAttribute("categories")
    public List<CategoryDTO> getCategories() {
        return categoryService.findAll().stream().map(item -> {
            CategoryDTO dto = new CategoryDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).toList();
    }

    @RequestMapping("")
    public String showProducts( ModelMap model,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.findPaginated(pageable);
        model.addAttribute("page", productPage);
        return "admin/products/Products";
    }


    @GetMapping("add")
    public String add(ModelMap model) {
        model.addAttribute("product", new ProductDTO());
        return"admin/products/AddOrEdit";
    }

    @PostMapping("/saveOrUpdate")
    public ModelAndView addProduct(@Valid @ModelAttribute("product") ProductDTO dto, BindingResult result,
                                   @RequestParam("imageFile") MultipartFile imageFile, ModelMap model) throws IOException {
        if (result.hasErrors()) {
            return new ModelAndView("admin/products/AddOrEdit", model);
        }
        // Lưu ảnh nếu có
        if (!imageFile.isEmpty()) {
            String imageUrl = productService.saveImage(imageFile);
            dto.setImage(imageUrl);
        }
        dto.setEnteredDate(new Date());
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);
        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        entity.setCategory(category);
        productService.save(entity);
        model.addAttribute("message", "Product is saved!");
        return new ModelAndView("redirect:/products", model);
    }

    @GetMapping("edit/{productId}")
    public ModelAndView edit(ModelMap model, @PathVariable("productId") Long productId) {

        Optional<Product> opt = productService.findById(productId);
        ProductDTO dto = new ProductDTO();

        if(opt.isPresent()) {
            Product entity = opt.get();

            BeanUtils.copyProperties(entity, dto);

            dto.setCategoryId(entity.getCategory().getCategoryId());
            dto.setIsEdit(true);

            model.addAttribute("product", dto);
            return new ModelAndView("admin/products/AddOrEdit", model);
        }
        model.addAttribute("message", "Product is not existed");

        return new ModelAndView("forward:/products", model);
    }
}
