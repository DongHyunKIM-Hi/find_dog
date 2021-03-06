package com.example.find_dog.model.entity;

import com.example.find_dog.model.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Article extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String nickname;

    @Column
    private String content;

    @Column(nullable = false)
    private String imgUrl;

    //좋아요 기능 구현
    @Column
    private Long likeCnt;

    //좋아요 누른 아이디 리스트
    @Column
    @ElementCollection
    private List<String> likeId = new ArrayList<>();

    public Article(ArticleRequestDto requestDto){
        this.nickname = requestDto.getNickname();
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.likeCnt = requestDto.getLikeCnt();
        this.likeId = requestDto.getLikeId();

    }

    public void update(ArticleRequestDto requestDto){
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.likeCnt = requestDto.getLikeCnt();
        this.likeId = requestDto.getLikeId();
    }

}
