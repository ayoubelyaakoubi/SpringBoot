package com.Tp5.Tp5.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Tp5.Tp5.Controller.AuthController;
import com.Tp5.Tp5.Model.Role;
import com.Tp5.Tp5.Model.User;
import com.Tp5.Tp5.Repository.RoleRepository;
import com.Tp5.Tp5.Repository.UserRepository;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
  

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        boolean exists = userRepository.existsByUsername(username);
        logger.info("Checking if username '{}' exists: {}", username, exists);
        return exists;
    }

    @Override
    public boolean existsByEmail(String email) {
        boolean exists = userRepository.existsByEmail(email);
        logger.info("Checking if email '{}' exists: {}", email, exists);
        return exists;
    }

    @Override
    public User save(User user) {
        logger.info("Attempting to save user with username: {}", user.getUsername());
        
        // Check if username already exists
        if (existsByUsername(user.getUsername())) {
            logger.error("Username {} already exists", user.getUsername());
            throw new RuntimeException("Username already exists");
        }

        // Check if email already exists
        if (existsByEmail(user.getEmail())) {
            logger.error("Email {} already exists", user.getEmail());
            throw new RuntimeException("Email already exists");
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Add ROLE_USER
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            logger.error("ROLE_USER not found in database");
            throw new RuntimeException("Default role not found");
        }
        user.addRole(userRole);
        
        logger.info("Saving new user to database");
        return userRepository.save(user);
    }
}