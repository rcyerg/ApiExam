package com.apiexam.ApiExam.repos;

import com.apiexam.ApiExam.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {

    @Query(value = "SELECT * FROM posts WHERE user_id = ?1", nativeQuery = true)
    public Iterable<Post> getAllByUserId(Long userId);
}
