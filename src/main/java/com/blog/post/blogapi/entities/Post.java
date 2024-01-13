package com.blog.post.blogapi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@Setter
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    @Column(length = 100,nullable = false)
    private String title;
    @Column(length = 1000)
    private String content;

    private String imageName;
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    @ManyToOne
    private User user;


    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
   private Set<Comments> commentsSet=new HashSet<>();



}
