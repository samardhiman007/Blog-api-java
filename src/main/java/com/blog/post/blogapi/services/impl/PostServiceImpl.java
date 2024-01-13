package com.blog.post.blogapi.services.impl;

import com.blog.post.blogapi.entities.Category;
import com.blog.post.blogapi.entities.Post;
import com.blog.post.blogapi.entities.User;
import com.blog.post.blogapi.exceptions.ResourceNotFoundException;
import com.blog.post.blogapi.payloads.PostDto;
import com.blog.post.blogapi.payloads.PostResponse;
import com.blog.post.blogapi.repositories.CategoryRepo;
import com.blog.post.blogapi.repositories.PostRepo;
import com.blog.post.blogapi.repositories.UserRepo;
import com.blog.post.blogapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category ID",categoryId));
        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost=this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post ID",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(post.getContent());

        Post updatePost=this.postRepo.save(post);

        return this.modelMapper.map(updatePost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post ID",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy) {
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());
        Page<Post> pagePost =this.postRepo.findAll(pageable);
        List<Post> postList=pagePost.getContent();
        List<PostDto> postDtoList=postList.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse= new PostResponse();
        postResponse.setPostDtoList(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPazeSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
       Post post=  this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post ID",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category ID",categoryId));
        List<Post> postList=this.postRepo.findByCategory(category);

        List<PostDto> postDtoList=postList.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtoList;

    }
    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User ID",userId));
        List<Post> postList=this.postRepo.findByUser(user);
        List<PostDto> postDtoList=postList.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtoList;

    }

    @Override
    public List<PostDto> searchPosts(String keyword) {

        List<Post> postList=this.postRepo.searchByTitle("%"+keyword+"%");
        List<PostDto> postDtoList=postList.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }
}
