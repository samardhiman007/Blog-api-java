package com.blog.post.blogapi.payloads;

import com.blog.post.blogapi.entities.Category;
import com.blog.post.blogapi.entities.Comments;
import com.blog.post.blogapi.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String title;
    private String content;


    private String imageName;
    private Date addedDate;


    private CategoryDto category;

    private UserDto user;

    private Set<CommentsDto> commentsSet=new HashSet<>();
}
