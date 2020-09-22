package com.minh.repository;
import com.minh.model.Category;
import com.minh.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    Iterable<User> findAllByCategory(Category category);
    List<User> findUsersByUserNameContaining(String userName);
}
