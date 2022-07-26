package com.fastcampus.dto;

import com.fastcampus.domain.Post;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class PostForm {
    private int id;

    private String title;
    private String content;

    public Post toEntity() {
        return new Post();
    }
}
