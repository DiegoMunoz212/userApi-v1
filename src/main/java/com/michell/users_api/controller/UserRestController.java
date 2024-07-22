package com.michell.users_api.controller;

import com.michell.users_api.entity.User;
import com.michell.users_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api-v1")
public class UserRestController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        User user = userService.findById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucb) {
        userService.save(user);
        URI locationOfNewUser = ucb
                .path("/api-v1/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewUser).build();
    }


    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User userRequest) {
//        user.setUpdatedAt(new Date());
        User user = userService.findById(userId);
        if (user != null) {
            user.setUsername(userRequest.getUsername() == null ? user.getUsername() : userRequest.getUsername());
            user.setPassword(userRequest.getPassword() == null ? user.getPassword() : userRequest.getPassword());
            user.setEmail(userRequest.getEmail() == null ? user.getEmail() : userRequest.getEmail());
            user.setUpdatedAt(new Date());
            userService.update(user);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
