package com.example.find_dog.controller;

import com.example.find_dog.model.dto.UserDto;
import com.example.find_dog.model.entity.User;
import com.example.find_dog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입을 위한 api
    @PostMapping("/user/regist")
    public ResponseEntity<User> signup(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    /*
    * 현재 접속한 유저의 정보를 리턴해주는 API
    * getMyUserInfo 메서드는 @PreAuthorize를 통해서 USER, ADMIN 두가지 권한 모두 허용
    * */
    @GetMapping("/api/check")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    /*
    * username을 기준으로 해당 유저의 정보를 리턴해주는 API
    * ADMIN 권한만 호출할 수 있도록 설
    * */
    @GetMapping("/api/check/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }
}