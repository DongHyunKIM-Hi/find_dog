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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;

    @Column
    private Long likeCnt;

    @Column
    @ElementCollection
    private List<String> likeId = new ArrayList<>();

    public Article(ArticleRequestDto requestDto){
        this.username = requestDto.getUsername();
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
