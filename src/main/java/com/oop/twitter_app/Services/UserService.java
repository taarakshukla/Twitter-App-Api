package com.oop.twitter_app.Services;

import com.oop.twitter_app.Entities.User;
import com.oop.twitter_app.Outputs.ErrorMessage;
import com.oop.twitter_app.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Forbidden, Account already exists");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        userRepository.save(user);
        String success = "Account Creation Successful";
        return ResponseEntity.ok(success);
    }


    public ResponseEntity<?> loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                String success = "Login Successful";
                return ResponseEntity.ok(success);
            } else {
                ErrorMessage errorMessage = new ErrorMessage("Username/Password Incorrect");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
        } else {
            ErrorMessage errorMessage = new ErrorMessage("User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public User getUserDetail(Long userID) {
        try {
            Optional<User> userOptional = userRepository.findById(userID);
            return userOptional.orElse(null);
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
