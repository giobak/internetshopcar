package com.example.internetshopcar.services;

import com.example.internetshopcar.entities.Product;
import com.example.internetshopcar.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    @Override
    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void updateProduct(int productId, Product product) {
        Product existingProduct = getProductById(productId);
        existingProduct.setpName(product.getpName());
        existingProduct.setpDesc(product.getpDesc());
        existingProduct.setpPhoto(product.getpPhoto());
        existingProduct.setpPrice(product.getpPrice());
        existingProduct.setpDiscount(product.getpDiscount());
        existingProduct.setpQuantity(product.getpQuantity());
        existingProduct.setCategories(product.getCategories());
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(int productId) {
        Product existingProduct = getProductById(productId);
        productRepository.delete(existingProduct);
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        // Retrieve products by category ID from the repository
        return productRepository.findByCategories_CategoryId(categoryId);
    }
}
