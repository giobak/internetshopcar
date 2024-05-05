package com.example.internetshopcar.services;

import com.example.internetshopcar.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int productId);
    Product addProduct(Product product);
    void updateProduct(int productId, Product product);
    void deleteProduct(int productId);

    List<Product> getProductsByCategoryId(int categoryId);
}
