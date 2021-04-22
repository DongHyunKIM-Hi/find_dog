package com.example.find_dog.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

// 로그인시 사용할 Dto
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    private String username;    // email주소를 담는 칼럼(nickname과는 별개!)

    @NotNull
    private String password;
}