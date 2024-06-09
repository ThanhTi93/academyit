package com.thanhti.academyit.controller;

import com.thanhti.academyit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    ProductService productService ;

    @RequestMapping("")
    public  String showHome(Model model) {

       model.addAttribute("listMobile",  productService.findAllMobile());

        return "client/home/Home";
    }

    @GetMapping("/detail")
    public  String showDetail(Model model) {

        return "client/detail/DetailProduct";
    }
}
