package com.example.internetshopcar.controller;

import com.example.internetshopcar.entities.Category;
import com.example.internetshopcar.entities.Product;
import com.example.internetshopcar.services.CategoryService;
import com.example.internetshopcar.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/index")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index"; // Assuming you have a view named "list.html" in the "product" directory
    }

    @GetMapping("/{productId}")
    public String getProductById(@PathVariable int productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "product/details"; // Assuming you have a view named "details.html" in the "product" directory
    }

    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        return "index"; // Assuming you have a view named "add.html" in the "product" directory
    }

//    @PostMapping("/addProduct")
//    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
//        try {
//            Product addedProduct = productService.addProduct(product);
//            redirectAttributes.addAttribute("productId", addedProduct.getpId());
//        } catch (Exception e) {
//            // Handle exception
//        }
//        return "redirect:/products/index";
//    }


    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile file, @RequestParam("categories") List<Integer> categoryIds, RedirectAttributes redirectAttributes) {
        try {
            // Handle file upload
            if (!file.isEmpty()) {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                String uploadDir = "path/to/your/upload/directory"; // Set your upload directory path
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                    product.setpPhoto(fileName);
                } catch (IOException e) {
                    // Handle file upload exception
                }
            }

            // Retrieve categories by their IDs
            List<Category> categories = categoryService.getCategoriesByIds(categoryIds);

            // Associate categories with the product
            product.setCategories(new HashSet<>(categories));

            // Save the product
            Product addedProduct = productService.addProduct(product);
            redirectAttributes.addAttribute("productId", addedProduct.getpId());
        } catch (Exception e) {
            // Handle exception
        }
        return "redirect:/products/index";
    }







    @GetMapping("/{productId}/edit")
    public String showEditProductForm(@PathVariable int productId, Model model) {
        Product product = productService.getProductById(productId);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "product/edit"; // Assuming you have a view named "edit.html" in the "product" directory
    }

    @PostMapping("/{productId}/edit")
    public String editProduct(@PathVariable int productId, @ModelAttribute Product product) {
        productService.updateProduct(productId, product);
        return "redirect:/products/list";
    }

    @GetMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return "redirect:/products/list";
    }
}