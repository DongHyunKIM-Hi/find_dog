package com.example.find_dog.model.dto;

import lombok.*;

// Token 정보를 Response할 때 사용할 Dto
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String token;
}
