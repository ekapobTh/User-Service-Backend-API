package com.example.userservice.service;

import com.example.userservice.model.User;
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

    // Find a user by their ID
    public User getUserById(Long id) {
        return userDictionary.get(id);
    }

    // Update a user by ID
    public User updateUser(User user) {
        if (userDictionary.containsKey(user.getId())) {
            userDictionary.put(user.getId(), user);
            return user;
        }
        return null;
    }

    // Delete a user by their ID
    public Boolean deleteUser(Long id) {
        Boolean isUserAvailable = userDictionary.containsKey(id);
        if(isUserAvailable)
            userDictionary.remove(id);
        return isUserAvailable;
    }
}
