package com.onion.controller;

import com.onion.entity.User;
import com.onion.model.UserModel;
import com.onion.repository.UserRepository;
import com.onion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User Register(@RequestBody User user){
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User Login(@RequestBody User user){
        User oldUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return oldUser;
    }

    @GetMapping("/users")
    public List<UserModel> getAllUser(){
        return userService.getAllUser();
    }
}
