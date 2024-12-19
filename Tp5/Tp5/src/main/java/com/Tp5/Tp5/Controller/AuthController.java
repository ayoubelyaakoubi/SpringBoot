package com.Tp5.Tp5.Controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


import com.Tp5.Tp5.Dto.UserDto;
import com.Tp5.Tp5.Model.User;
import com.Tp5.Tp5.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout) {
        
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "auth/login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        logger.info("Showing registration form");
        model.addAttribute("user", new UserDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDto userDto, 
                             RedirectAttributes redirectAttributes) {
        logger.info("Processing registration for username: {}", userDto.getUsername());
        
        try {
            
            if (userDto.getUsername() == null || userDto.getUsername().trim().isEmpty()) {
                throw new IllegalArgumentException("Username cannot be empty");
            }
            if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }
            if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
                throw new IllegalArgumentException("Password cannot be empty");
            }

            // Create new user
            User user = new User();
            user.setUsername(userDto.getUsername().trim());
            user.setEmail(userDto.getEmail().trim());
            user.setPassword(userDto.getPassword());

            // Try to save
            userService.save(user);
            
            logger.info("Successfully registered user: {}", userDto.getUsername());
            redirectAttributes.addFlashAttribute("success", 
                "Registration successful! You can now login.");
            return "redirect:/login";
            
        } catch (Exception e) {
            logger.error("Registration failed for username: {}", userDto.getUsername(), e);
            redirectAttributes.addFlashAttribute("error", 
                "Registration failed: " + e.getMessage());
            return "redirect:/register";
        }
    }
}