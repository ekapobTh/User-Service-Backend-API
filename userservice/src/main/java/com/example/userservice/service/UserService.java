package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final Map<Long, User> userDictionary = new HashMap<>();
    private long idCounter = 1;  // To simulate auto-generated IDs

    // Save a user in the "dictionary" (in-memory map)
    public User saveUser(User user) {
        user.setId(idCounter++);  // Assign a unique ID to the user
        userDictionary.put(user.getId(), user);  // Save the user in the dictionary
        return user;
    }

    // Get all users from the dictionary
    public Collection<User> getAllUsers() {
        return userDictionary.values();
    }

    // Optional: Find a user by their ID
    public User getUserById(Long id) {
        return userDictionary.get(id);
    }

    // Optional: Delete a user by their ID
    public void deleteUser(Long id) {
        userDictionary.remove(id);
    }
}
