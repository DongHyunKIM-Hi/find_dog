package com.example.find_dog.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Mbti_test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String MBTI;

    @Column(nullable = false)
    private String QA;

    @Column(nullable = false)
    private String Q1;

    @Column(nullable = false)
    private String Q2;

    @Column(nullable = false, length = 1000)
    private String img;


    public Mbti_test(String MBTI,String QA, String Q1, String Q2, String img){
        this.MBTI = MBTI;
        this.QA = QA;
        this.Q1 = Q1;
        this.Q2 =Q2;
        this.img = img;
    }
}
