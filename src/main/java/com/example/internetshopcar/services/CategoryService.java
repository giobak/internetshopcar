package com.example.internetshopcar.services;

import com.example.internetshopcar.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    void saveCategory(Category category);

    Category getCategoryById(int categoryId);

    void updateCategory(int categoryId, Category category);

    void deleteCategory(int categoryId);

    List<Category> getCategoriesByIds(List<Integer> categoryIds);
}
