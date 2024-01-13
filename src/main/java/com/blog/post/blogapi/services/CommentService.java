package com.blog.post.blogapi.services;

import com.blog.post.blogapi.payloads.CommentsDto;

public interface CommentService {

    CommentsDto createComment(CommentsDto commentsDto,Integer postId);
    void deleteComment(Integer commentId);



}
