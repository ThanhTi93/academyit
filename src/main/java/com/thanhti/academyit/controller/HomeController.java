package com.thanhti.academyit.controller;

import com.thanhti.academyit.dto.ProductDTO;
import com.thanhti.academyit.entity.Product;
import com.thanhti.academyit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    ProductService productService ;

    @RequestMapping("")
    public  String showHome(Model model) {

       model.addAttribute("listMobile",  productService.findAllMobile());

        return "client/home/Home";
    }

    @GetMapping("/detail/{productId}")
    public  String showDetail(ModelMap model, @PathVariable("productId") Long productId) {

        Optional<Product> opt = productService.findById(productId);
        if(opt.isPresent()) {
            Product entity = opt.get();
            model.addAttribute("product", entity);
            return "client/detail/DetailProduct";
        }
        model.addAttribute("message", "Product is not existed");
        return "";
    }
}
