package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    // Constructor injection of UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST request to save a new user
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        System.out.printf("BBBL TEst %s",user);
        User savedUser = userService.saveUser(user);  // Saves the user in the dictionary
        return ResponseEntity.ok(savedUser);  // Return the saved user
    }

    @GetMapping("/hello")
    public String hello(){
        return String.format("Hello World!");
    }

    // GET request to fetch all users
    @GetMapping
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();  // Return all users from the dictionary
    }

    // Optional: GET request to fetch a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // Optional: DELETE request to delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
