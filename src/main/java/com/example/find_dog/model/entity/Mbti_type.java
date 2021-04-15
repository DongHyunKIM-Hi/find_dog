package com.example.find_dog.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mbti_type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 1000)
    private String img;

    @Column(nullable = false)
    private String dog;

    public Mbti_type(String type,String content,String img,String dog){
        this.type = type;
        this.img=img;
        this.content = content;
        this.dog =dog;
    }
}