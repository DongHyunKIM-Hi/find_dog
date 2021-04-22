package com.example.find_dog.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleRequestDto {
//article 요청에 response하기 위한 dto
    private String nickname;
    private String content;
    private String imgUrl;
    private Long likeCnt;
    private List<String> likeId;


}
