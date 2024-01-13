package com.blog.post.blogapi.services.impl;

import com.blog.post.blogapi.entities.Comments;
import com.blog.post.blogapi.entities.Post;
import com.blog.post.blogapi.exceptions.ResourceNotFoundException;
import com.blog.post.blogapi.payloads.CommentsDto;
import com.blog.post.blogapi.repositories.CommentRepo;
import com.blog.post.blogapi.repositories.PostRepo;
import com.blog.post.blogapi.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentsDto createComment(CommentsDto commentsDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        Comments comments=this.modelMapper.map(commentsDto,Comments.class);

        comments.setPost(post);
        Comments comments1=this.commentRepo.save(comments);
        return this.modelMapper.map(comments1,CommentsDto.class);

    }

    @Override
    public void deleteComment(Integer commentId) {
        Comments comments=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment ID",commentId));
        this.commentRepo.delete(comments);
    }
}
