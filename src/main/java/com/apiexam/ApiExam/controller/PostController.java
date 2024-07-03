package com.apiexam.ApiExam.controller;

import com.apiexam.ApiExam.domain.Post;
import com.apiexam.ApiExam.service.PostService;
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
public class PostController {

    @Autowired
    private PostService postService;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    @PostMapping("users/{userId}/posts")
    public ResponseEntity<?> createPost(@PathVariable Long userId, @Valid @RequestBody Post post){

        Post createdPost = postService.createAPost(userId, post);

        logger.info("Created post successfully");

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId){

        Post retrievedPost = postService.getAPostById(postId);

        logger.info("Retrieved post successfully");

        return new ResponseEntity<>(retrievedPost, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts(){

        Iterable<Post> allThePosts = postService.getAllThePosts();

        logger.info("Retrieved all posts successfully");

        return new ResponseEntity<>(allThePosts, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){

        postService.deleteAPost(postId);

        logger.info("Deleted post successfully");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @Valid @RequestBody Post updatedPost){

        postService.updateAPost(postId, updatedPost);

        logger.info("Updated post successfully");

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<?> getPostsByUserId(@PathVariable Long userId){

        Iterable<Post> retrievedPosts = postService.getAllPostsByUserId(userId);

        logger.info("All posts retrieved successfully");

        return new ResponseEntity<>(retrievedPosts, HttpStatus.OK);
    }
}

