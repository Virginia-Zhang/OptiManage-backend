package com.virginia.controller;

import com.virginia.result.R;
import com.virginia.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("/products")
    public R getAllProducts() {
        return R.SUCCESS(productService.getAllProducts());
    }
}
