package com.example.find_dog.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleRequestDto {

    private String title;
    private String username;
    private String content;
    private String imgUrl;


}