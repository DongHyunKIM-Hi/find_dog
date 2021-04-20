package com.example.find_dog.model.entity;

import com.example.find_dog.model.dto.ArticleRequestDto;
import com.example.find_dog.model.dto.CommentRequestDto;
import com.example.find_dog.repository.CommentRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String comment;

    @Column
    private String nickname;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Article article;
    
    public Comment(CommentRequestDto requestDto, Article article){
        this.nickname = requestDto.getNickname();
        this.comment = requestDto.getComment();
        this.article = article;
    }
    public void update(CommentRequestDto requestDto){
        this.nickname = requestDto.getNickname();
        this.comment = requestDto.getComment();
    }


}
