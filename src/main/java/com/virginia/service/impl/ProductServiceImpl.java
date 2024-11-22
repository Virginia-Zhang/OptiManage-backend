package com.virginia.service.impl;

import com.virginia.mapper.ProductMapper;
import com.virginia.pojo.Product;
import com.virginia.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public Integer addProduct(Product product) {
        return 0;
    }

    @Override
    public Integer editProduct(Product product) {
        return 0;
    }

    @Override
    public List<Product> getAllProducts() {
        return productMapper.selectAllProducts();
    }
}
