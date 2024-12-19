package com.Tp5.Tp5.Service;

import java.util.List;

import com.Tp5.Tp5.Model.User;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
    List<User> findAll();
    void deleteById(Long id);
    User update(User user);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}