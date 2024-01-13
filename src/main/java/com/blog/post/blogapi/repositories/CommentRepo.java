package com.blog.post.blogapi.repositories;

import com.blog.post.blogapi.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comments,Integer> {



}
