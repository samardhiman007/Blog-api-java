package com.blog.post.blogapi.controllers;


import com.blog.post.blogapi.payloads.ApiResponse;
import com.blog.post.blogapi.payloads.CommentsDto;
import com.blog.post.blogapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/{postId}")
    public ResponseEntity<CommentsDto> addComment(@RequestBody CommentsDto commentsDto, @PathVariable Integer postId){

        CommentsDto commentsDto1=this.commentService.createComment(commentsDto,postId);
        return new ResponseEntity<CommentsDto>(commentsDto1, HttpStatus.CREATED);

    }

    public ResponseEntity<ApiResponse> deteleComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully!!",true),HttpStatus.OK);
    }

}
