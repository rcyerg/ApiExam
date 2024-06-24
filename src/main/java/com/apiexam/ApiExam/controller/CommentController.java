package com.apiexam.ApiExam.controller;

import com.apiexam.ApiExam.domain.Comment;
import com.apiexam.ApiExam.domain.Post;
import com.apiexam.ApiExam.service.CommentService;
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
public class CommentController {

    @Autowired
    private CommentService commentService;

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> createComment(@PathVariable Long postId, @Valid @RequestBody Comment comment){

        Comment createdComment = commentService.createAComment(postId,comment);

        logger.info("Created comment successfully");

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<?> getCommentById(@PathVariable Long commentId){

        Comment retrievedComment = commentService.getACommentById(commentId);

        logger.info("Comment retrieved successfully");

        return new ResponseEntity<>(retrievedComment, HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getAllComments(){

        Iterable<Comment> retrievedComments = commentService.getAllTheComments();

        logger.info("Retrieved all comments");

        return new ResponseEntity<>(retrievedComments, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable Long postId){

        Iterable<Comment> retrievedComments = commentService.getAllCommentsByPostId(postId);

        logger.info("All comments retrieved successfully");

        return new ResponseEntity<>(retrievedComments, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){

        commentService.deleteAComment(commentId);

        logger.info("Deleted comment successfully");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @Valid @RequestBody Comment updatedComment){

        commentService.updateAComment(commentId, updatedComment);

        logger.info("Updated comment successfully");

        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }
}
