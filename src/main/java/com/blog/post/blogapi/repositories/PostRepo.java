package com.blog.post.blogapi.repositories;

import com.blog.post.blogapi.entities.Category;
import com.blog.post.blogapi.entities.Post;
import com.blog.post.blogapi.entities.User;
import com.blog.post.blogapi.payloads.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
@Query("select p from Post p where p .title like :key")
    List<Post> searchByTitle(@Param("key") String title);

}
