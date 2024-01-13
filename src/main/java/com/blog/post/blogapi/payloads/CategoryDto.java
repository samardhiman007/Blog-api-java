package com.blog.post.blogapi.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

    private Integer categrogyId;
    private String categoryTitle;
    private String categoryDescription;
}
