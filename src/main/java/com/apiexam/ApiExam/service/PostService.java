package com.apiexam.ApiExam.service;

import com.apiexam.ApiExam.domain.Post;
import com.apiexam.ApiExam.domain.User;
import com.apiexam.ApiExam.exception.ConflictException;
import com.apiexam.ApiExam.exception.ResourceNotFoundException;
import com.apiexam.ApiExam.repos.PostRepo;
import com.apiexam.ApiExam.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserService userService;

    public Post createAPost(Long userId, Post post) {

        User user = userService.getAUserById(userId);

        user.getPosts().add(post);

        return postRepo.save(post);
    }

    public Post getAPostById(Long postId) {

        if (postRepo.findById(postId).isEmpty()){
            throw new ResourceNotFoundException("Post not found");
        }

        return postRepo.findById(postId).get();

    }

    public Iterable<Post> getAllThePosts() {

        return postRepo.findAll();
    }

    public void deleteAPost(Long postId) {

        if (postRepo.findById(postId).isEmpty()){
            throw new ResourceNotFoundException("Post not found");
        }

        postRepo.deleteById(postId);
    }

    public void updateAPost(Long postId, Post updatedPost) {

        if (postRepo.findById(postId).isEmpty()){
            throw new ResourceNotFoundException("Post not found");
        }

        if (!postId.equals(updatedPost.getId())){
            throw new ConflictException("postId must match to update");
        }

        postRepo.save(updatedPost);

    }

    public Iterable<Post> getAllPostsByUserId(Long userId) {

        return postRepo.getAllByUserId(userId);
    }
}
