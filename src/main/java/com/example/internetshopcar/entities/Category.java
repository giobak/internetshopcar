package com.example.internetshopcar.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20, name = "category_id")
    private int categoryId;

    @Column(length = 100, name = "category_title")
    private String categoryTitle;

    @Column(length = 200, name = "category_description")
    private String categoryDescription;


 //   @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
 //   private List<Product> products = new ArrayList<>();

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();




    public Category(int categoryId, String categoryTitle, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryDescription = categoryDescription;
    }

    public Category(String categoryTitle, String categoryDescription, List<Product> products) {
        this.categoryTitle = categoryTitle;
        this.categoryDescription = categoryDescription;
        this.products = (Set<Product>) products;
    }

    public Category(String categoryTitle, String categoryDescription, Set<Product> products) {
        this.categoryTitle = categoryTitle;
        this.categoryDescription = categoryDescription;
        this.products = products;
    }

    public Category(int categoryId, String categoryTitle, String categoryDescription, Set<Product> products) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryDescription = categoryDescription;
        this.products = products;
    }

    public Category() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", products=" + products +
                '}';
    }
}
