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
        User savedUser = userService.saveUser(user);  // Saves the user in the dictionary
        return ResponseEntity.ok(savedUser);  // Return the saved user
    }

    // GET request to fetch all users
    @GetMapping
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();  // Return all users from the dictionary
    }

    // GET request to fetch a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // PUT request to update a user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        // Check if the user exists
        User existingUser = userService.getUserById(id);

        if (existingUser == null) {
            return ResponseEntity.notFound().build();  // Return 404 if user is not found
        }

        // Update the user details
        user.setId(id);  // Ensure the user ID remains the same
        User updatedUser = userService.updateUser(user);  // Service method to update the user

        return ResponseEntity.ok(updatedUser);  // Return the updated user
    }

    // DELETE request to delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Boolean isSuccess = userService.deleteUser(id);
        return isSuccess ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
