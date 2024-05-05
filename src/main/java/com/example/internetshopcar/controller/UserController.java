package com.example.internetshopcar.controller;


import com.example.internetshopcar.entities.Category;
import com.example.internetshopcar.entities.Product;
import com.example.internetshopcar.entities.User;
import com.example.internetshopcar.services.CategoryService;
import com.example.internetshopcar.services.ProductService;
import com.example.internetshopcar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("user") User user) {
        String result = "error";
        System.out.println(user);
        if(user.getUserPassword().equals(user.getUserCPassword())) {
            try {
                userService.registerUser(user);
                result = "registrationSuccess";
            }
            catch (Exception e) {
                result = "error";
            }
        }
        return result;
    }


 //   @PostMapping("/loginUser")
 //   public String loginUser(@ModelAttribute("user") User user, Model model) {
 //       String result = "invalidlogin";

        // Fetch the user from the database based on the provided email
 //       User existingUser = service.findUserByEmail(user.getUserEmail());

 //       if (existingUser != null && existingUser.getUserPassword().equals(user.getUserPassword())) {
            // Authentication successful
 //           result = "loginSuccess";
 //           model.addAttribute("loggedInUser", existingUser);
 //       } else {
            // Authentication failed
 //           model.addAttribute("loginError", "Invalid email or password. Please try again.");
 //       }

 //       return result;
 //   }



    @PostMapping("/loginUser")
    public String currentUser(@ModelAttribute("user") User user, Model model) {
        String result = "invalidlogin";

        // Fetch the user from the database based on the provided email
        User existingUser = userService.findUserByEmail(user.getUserEmail());

        if (existingUser != null && existingUser.getUserType().equals("admin") && existingUser.getUserPassword().equals(user.getUserPassword())) {
            // Authentication successful for admin
            result = "admin";
            model.addAttribute("loggedInUser", existingUser);
        } else if (existingUser != null && existingUser.getUserType().equals("normal") && existingUser.getUserPassword().equals(user.getUserPassword())) {
            // Authentication successful for normal user
            result = "normal";
            model.addAttribute("loggedInUser", existingUser);
        } else {
            // Authentication failed
            model.addAttribute("loginError", "Invalid email or password. Please try again.");
        }
        return result;
    }







    @GetMapping("/index1")
    public String viewHomePage(Model model) {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList"; // Assuming you have a view named userList.html
    }



    @GetMapping("/index")
    public String getAllProductsAndCategories(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "index";
    }

}
