package com.example.userservice.service;

import com.example.userservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Initialize the UserService before each test
        userService = new UserService();
    }

    @Test
    public void testSaveUser() {
        // Create a new user
        User user = new User();
        user.setUsername("John");
        user.setEmail("john@example.com");

        // Save the user
        User savedUser = userService.saveUser(user);

        // Assert that the user is saved correctly
        assertNotNull(savedUser.getId(), "User ID should not be null");
        assertEquals("John", savedUser.getUsername(), "Username should be 'John'");
        assertEquals("john@example.com", savedUser.getEmail(), "Email should match");
    }

    @Test
    public void testGetAllUsers() {
        // Add some users
        User user1 = new User();
        user1.setUsername("John");
        user1.setEmail("john@example.com");

        User user2 = new User();
        user2.setUsername("Jane");
        user2.setEmail("jane@example.com");

        userService.saveUser(user1);
        userService.saveUser(user2);

        // Retrieve all users
        Collection<User> allUsers = userService.getAllUsers();

        // Assert that we have the correct number of users
        assertEquals(2, allUsers.size(), "There should be 2 users in the dictionary");
    }

    @Test
    public void testGetUserById() {
        // Create and save a user
        User user = new User();
        user.setUsername("John");
        user.setEmail("john@example.com");
        User savedUser = userService.saveUser(user);

        // Retrieve the user by ID
        User retrievedUser = userService.getUserById(savedUser.getId());

        // Assert that the retrieved user matches the saved user
        assertNotNull(retrievedUser, "The retrieved user should not be null");
        assertEquals(savedUser.getId(), retrievedUser.getId(), "User IDs should match");
    }

    @Test
    public void testUpdateUser() {
        // Create and save a user
        User user = new User();
        user.setUsername("John");
        user.setEmail("john@example.com");
        User savedUser = userService.saveUser(user);

        // Update user details
        savedUser.setUsername("John Updated");
        savedUser.setEmail("john.updated@example.com");

        // Update the user
        User updatedUser = userService.updateUser(savedUser);

        // Assert that the user has been updated
        assertNotNull(updatedUser, "The updated user should not be null");
        assertEquals("John Updated", updatedUser.getUsername(), "Username should be updated");
        assertEquals("john.updated@example.com", updatedUser.getEmail(), "Email should be updated");
    }

    @Test
    public void testDeleteUser() {
        // Create and save a user
        User user = new User();
        user.setUsername("John");
        user.setEmail("john@example.com");
        User savedUser = userService.saveUser(user);

        // Delete the user
        Boolean isDeleted = userService.deleteUser(savedUser.getId());

        // Assert that the user is deleted
        assertTrue(isDeleted, "The user should be deleted successfully");
        assertNull(userService.getUserById(savedUser.getId()), "The user should not exist after deletion");
    }

    @Test
    public void testDeleteNonExistingUser() {
        // Attempt to delete a non-existing user
        Boolean isDeleted = userService.deleteUser(999L); // ID 999 doesn't exist

        // Assert that the deletion returns false
        assertFalse(isDeleted, "The deletion should return false for non-existing user");
    }
}
