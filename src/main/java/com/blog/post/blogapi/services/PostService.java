package com.blog.post.blogapi.services;

import com.blog.post.blogapi.entities.Post;
import com.blog.post.blogapi.payloads.PostDto;
import com.blog.post.blogapi.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    void deletePost(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);
}
