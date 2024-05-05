
package com.example.internetshopcar.controller;

        import com.example.internetshopcar.entities.Category;
        import com.example.internetshopcar.entities.Product;
        import com.example.internetshopcar.services.CategoryService;
        import com.example.internetshopcar.services.ProductService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


//    @GetMapping("/")
//    public List<Category> getAllCategories() {
//        List<Category> categoryList = categoryService.getAllCategories();
//        return categoryService.getAllCategories();
//    }



    @GetMapping("/showAllCategories")
    public String getAllCategories(Model model) {
        // Retrieve all categories from ProductService
        List<Category> categories = categoryService.getAllCategories();
        // Add categories to the model
        model.addAttribute("categories", categories);
        // Return the name of the Thymeleaf template to render
        return "showAllCategories"; // Assuming your HTML file is named index.html
    }



    @GetMapping("/admin")
    public String adminPage(Model model) {
        Category category = new Category(); // Create a new Category object
        model.addAttribute("category", category); // Add the category object to the model
        // Add any other necessary attributes to the model
        return "admin"; // Return the name of your Thymeleaf template
    }


    @PostMapping("/saveCategory")
    public String addCategory(@ModelAttribute("category") Category category) {
        String result = "error";
        try {
            categoryService.saveCategory(category);
            result = "categorySavedSuccess";
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

/*    @PostMapping("/saveCategory")
    public String saveCategory(@RequestBody Category category) {
        try {
            categoryService.saveCategory(category);
            return "Category saved successfully!";
        } catch (Exception e) {
            return "Error saving category: " + e.getMessage();
        }
    }
*/



    @GetMapping("/categoryDetails/{categoryId}")
    public String getCategoryById(@PathVariable int categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "categoryDetails";
    }






    @GetMapping("/categories")
    public String getProductsByCategory(@RequestParam(name = "categoryId", required = false) Integer categoryId, Model model) {
        List<Product> products;
        if (categoryId != null) {
            products = productService.getProductsByCategoryId(categoryId);
        } else {
            products = productService.getAllProducts();
        }
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "index";
    }






    @GetMapping("/{categoryId}")
    public String getCategoryById(@PathVariable int categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return "showCategoryById";
    }

    @PutMapping("/{categoryId}")
    public void updateCategory(@PathVariable int categoryId, @RequestBody Category category) {
        categoryService.updateCategory(categoryId, category);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
