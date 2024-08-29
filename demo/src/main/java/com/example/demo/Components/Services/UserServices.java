package com.example.demo.Components.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Components.Repository.*;
import com.example.demo.Components.*;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Integer id){
        Optional <User> result = userRepository.findById(id);
        return result.orElse(null);
    }

    public User login(User input) {
        // Find user by email
        User user = userRepository.findByEmail(input.getEmail());

        // Check if user exists and password matches
        if (user != null && user.getPassword() != null && user.getPassword().toString().equals(input.getPassword())) {
            return user;
        } else {
            return null; // User not found or password doesn't match
        }
    }


    public User addUser(User user) {
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            // Email already exists, return an error or throw an exception
            throw new IllegalArgumentException("Email already exists");
        }

        // Email doesn't exist, proceed to create the new user
        User userModel = new User();
        userModel.setEmail(user.getEmail());
        userModel.setName(user.getName());
        userModel.setPassword(user.getPassword());
        userModel.setPhone(user.getPhone());
        userRepository.save(userModel);
        return userModel;
    }


    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User updateUser(Integer id, User newUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            user.setPhone(newUser.getPhone());
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
