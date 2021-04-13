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

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Article article;
    
    public Comment(CommentRequestDto requestDto, Article article){
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.article = article;
    }
    public void update(CommentRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
    }

}
