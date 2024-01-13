package com.blog.post.blogapi.repositories;

import com.blog.post.blogapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer>{

}
