package com.apiexam.ApiExam.service;

import com.apiexam.ApiExam.domain.User;
import com.apiexam.ApiExam.exception.ConflictException;
import com.apiexam.ApiExam.exception.ResourceNotFoundException;
import com.apiexam.ApiExam.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createAUser(User user) {

      return userRepo.save(user);

    }

    public User getAUserById(Long userId) {

        if (userRepo.findById(userId).isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }

        return userRepo.findById(userId).get();

    }

    public Iterable<User> getAllTheUsers() {

        return userRepo.findAll();
    }

    public void deleteAUser(Long userId) {

        if (userRepo.findById(userId).isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }

        userRepo.deleteById(userId);
    }

    public void updateAUser(Long userId, User updatedUser) {

        if (userRepo.findById(userId).isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }

        if (!userId.equals(updatedUser.getId())){
            throw new ConflictException("userId must match to update");
        }

        userRepo.save(updatedUser);
    }
}
