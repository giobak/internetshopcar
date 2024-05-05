package com.example.internetshopcar.services;

import com.example.internetshopcar.entities.Category;
import com.example.internetshopcar.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        return optionalCategory.orElse(null);
    }

    @Override
    public void updateCategory(int categoryId, Category category) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setCategoryTitle(category.getCategoryTitle());
        existingCategory.setCategoryDescription(category.getCategoryDescription());
        categoryRepository.save(existingCategory);
    }
    @Override
    public void deleteCategory(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> getCategoriesByIds(List<Integer> categoryIds) {
        return null;
    }
}
