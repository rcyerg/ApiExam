package com.apiexam.ApiExam.service;

import com.apiexam.ApiExam.domain.Comment;
import com.apiexam.ApiExam.domain.Post;
import com.apiexam.ApiExam.domain.User;
import com.apiexam.ApiExam.exception.ConflictException;
import com.apiexam.ApiExam.exception.ResourceNotFoundException;
import com.apiexam.ApiExam.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired PostService postService;

    public Comment createAComment(Long postId, Comment comment) {

        Post post = postService.getAPostById(postId);

        post.getComments().add(comment);

        return commentRepo.save(comment);

    }

    public Comment getACommentById(Long commentId) {

        if (commentRepo.findById(commentId).isEmpty()){
            throw new ResourceNotFoundException("Comment not found");
        }

        return commentRepo.findById(commentId).get();

    }

    public Iterable<Comment> getAllTheComments() {

        return commentRepo.findAll();

    }

    public Iterable<Comment> getAllCommentsByPostId(Long postId) {

        return commentRepo.getAllByPostId(postId);

    }

    public void deleteAComment(Long commentId) {

        if (commentRepo.findById(commentId).isEmpty()){
            throw new ResourceNotFoundException("Comment not found");
        }

        commentRepo.deleteById(commentId);
    }

    public void updateAComment(Long commentId, Comment updatedComment) {

        if (commentRepo.findById(commentId).isEmpty()){
            throw new ResourceNotFoundException("Comment not found");
        }

        if (!commentId.equals(updatedComment.getId())){
            throw new ConflictException("commentId must match to update");
        }

        commentRepo.save(updatedComment);
    }
}
