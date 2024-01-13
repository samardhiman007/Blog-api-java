package com.blog.post.blogapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> postDtoList;
    private int pageNumber;
    private int pazeSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

}
