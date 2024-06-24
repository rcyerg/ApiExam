package com.apiexam.ApiExam.repos;

import com.apiexam.ApiExam.domain.Comment;
import com.apiexam.ApiExam.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comments WHERE post_id = ?1", nativeQuery = true)
    Iterable<Comment> getAllByPostId(Long postId);
}
