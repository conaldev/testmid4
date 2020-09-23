package com.minh.service;

import com.minh.model.Category;
import com.minh.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    User findById(Long id);
    void save(User user);
    void remove(Long id);
    Iterable<User> findAllByCategory(Category category);
    Page<User> findUsersByUserNameContaining(String userName,Pageable pageable);
    Page<User> search(String keyword,Pageable pageable);
}
