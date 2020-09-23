package com.minh.service;

import com.minh.model.Category;
import com.minh.model.User;
import com.minh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }

    @Override
    public Iterable<User> findAllByCategory(Category category) {
        return userRepository.findAllByCategory(category);
    }

    @Override
    public Page<User> findUsersByUserNameContaining(String userName, Pageable pageable) {
        return userRepository.findUsersByUserNameContaining(userName,pageable);
    }

    @Override
    public Page<User> search(String keyword, Pageable pageable) {
        return userRepository.findUsersByUserNameContaining(keyword,pageable);
    }
}
