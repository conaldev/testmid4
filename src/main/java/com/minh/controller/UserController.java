package com.minh.controller;


import com.minh.model.Category;
import com.minh.model.User;
import com.minh.service.CategoryService;
import com.minh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/create-user")
    public ModelAndView showCreatForm() {
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView saveUser(@ModelAttribute("user") User user)
    {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("message", "New user created successfully!");
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView listUsers(@PageableDefault(value = 7,sort = "userName")  Pageable pageable, Optional<String> s) {
        Page<User> users = s.isPresent() ? search(s,pageable) : getPage(pageable);
        ModelAndView modelAndView = new ModelAndView("/user/list");
        modelAndView.addObject("users", users);
        modelAndView.addObject("keyword",s.orElse(null));
        return modelAndView;
    }
    private Page<User> getPage(Pageable pageable){return userService.findAll(pageable);}
    private Page<User> search(Optional<String> s, Pageable pageable) {
        return userService.search(s.get(),pageable);
    }
    @GetMapping("/edit-user/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/edit");
            modelAndView.addObject("user", user);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }

    }

    @PostMapping("/edit-user")
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/user/edit");
        modelAndView.addObject("user", user);
        modelAndView.addObject("message", "User updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-user/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/delete");
            modelAndView.addObject("user", user);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-user")
    public String deleteUser(@ModelAttribute("user") User user){
        userService.remove(user.getId());
        return "redirect:users";
    }
}
