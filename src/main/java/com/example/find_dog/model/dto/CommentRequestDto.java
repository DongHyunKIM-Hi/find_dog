package com.example.find_dog.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
//comment 요청에 response 하기위한 dto
    private String comment;
    private String nickname;
}
