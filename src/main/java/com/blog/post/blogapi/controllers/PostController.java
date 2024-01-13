package com.blog.post.blogapi.controllers;

import com.blog.post.blogapi.entities.Post;
import com.blog.post.blogapi.payloads.ApiResponse;
import com.blog.post.blogapi.payloads.CategoryDto;
import com.blog.post.blogapi.payloads.PostDto;
import com.blog.post.blogapi.payloads.PostResponse;
import com.blog.post.blogapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {


    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto postDto1=this.postService.createPost(postDto,userId,categoryId);

        return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);

    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> postDtoList =this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtoList =this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber" ,defaultValue ="0" ,required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue = "1",required = false) Integer pageSize   ,
                                                  @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy){
        PostResponse postResponse =this.postService.getAllPosts(pageNumber,pageSize,sortBy);

        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    @GetMapping("/{postId}/posts")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto post= this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(post,HttpStatus.OK);

    }

    @DeleteExchange("/posts/{postId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer postId){
        this.postService.deletePost(postId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto postDto1=this.postService.updatePost(postDto,postId);

        return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);
    }

    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String keyword){
        List<PostDto> postDtoList=this.postService.searchPosts(keyword);

        return new ResponseEntity<List<PostDto>>(postDtoList,HttpStatus.OK);
    }

}
