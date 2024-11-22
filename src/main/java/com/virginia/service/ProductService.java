package com.virginia.service;

import com.virginia.pojo.Product;

import java.util.List;

/**
 * Service interface for Product related features
 * @author Virginia
 */
public interface ProductService {
    Integer addProduct(Product product);
    Integer editProduct(Product product);

    // Get all products with id and name only
    List<Product> getAllProducts();
}
