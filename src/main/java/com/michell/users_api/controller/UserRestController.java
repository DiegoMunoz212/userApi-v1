package com.michell.users_api.controller;

import com.michell.users_api.entity.User;
import com.michell.users_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-v1")
public class UserRestController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found -"+userId);
        }
        return user;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        user.setId(0);
        userService.save(user);
        return user;
    }


    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found -"+userId);
        }
        userService.deleteById(userId);

        return "User deleted - "+userId;

    }
}
