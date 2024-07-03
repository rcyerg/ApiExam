package com.apiexam.ApiExam.controller;

import com.apiexam.ApiExam.domain.User;
import com.apiexam.ApiExam.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){

        User createdUser = userService.createAUser(user);

        logger.info("Created user successfully");

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){

        User retrievedUser = userService.getAUserById(userId);

        logger.info("User retrieved successfully");

        return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){

        Iterable<User> retrievedUsers = userService.getAllTheUsers();

        logger.info("Retrieved all users");

        return new ResponseEntity<>(retrievedUsers, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){

        userService.deleteAUser(userId);

        logger.info("Deleted user successfully");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @Valid @RequestBody User updatedUser){

        userService.updateAUser(userId, updatedUser);

        logger.info("Updated user successfully");

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
