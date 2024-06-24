package com.apiexam.ApiExam.repos;

import com.apiexam.ApiExam.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {


}
