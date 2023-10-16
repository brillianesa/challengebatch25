package com.example.challenge.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.challenge.model.Product;
import com.example.challenge.repository.ProductRepository;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String form(Model model, @PathVariable(required = false) Integer id){
        if (id != null){
            model.addAttribute("product", productRepository.findById(id));
        }else{
            model.addAttribute("product", new Product());
        }
        return "product/form";
    }

    @PostMapping("save")
    public String save(Product product) {
        productRepository.save(product);
        if (productRepository.findById(product.getId()).isPresent()) {
            return "redirect:/product";
        } else {
            return "product/form";
        }
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable(required = true) Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.delete(product);
            return "redirect:/product";
        } else {
            return "redirect:/product";
        }
    }
}

