package com.example.find_dog.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Adoption extends Timestamped{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private String type;        // 견종

    @Column(nullable = true)
    private String age;         // 나이

    @Column(nullable = true)
    private String gender;      // 성별

    @Column(nullable = true)
    private String note;        // 특이사항

    @Column(nullable = true)
    private String vaccine;     // 접종유무

    @Column(nullable = true)
    private String neuter;      // 중성화유무

    @Column(nullable = true)
    private String reason;      // 보호소로 오게 된 이유

    @Column(nullable = true)
    private String before;      // 맡겨지기 전 가정 환경

    @Column(nullable = true)
    private String fave;        // 좋아하는 것

    @Column(nullable = true)
    private String dislike;     // 싫어하는 것

    @Column(nullable = true)
    private String image;       // 썸네일 이미지

    public Adoption(String type, String age, String gender, String note, String vaccine, String neuter, String reason, String before, String fave, String dislike, String image){
        this.type = type;
        this.age = age;
        this.gender = gender;
        this.note = note;
        this.vaccine = vaccine;
        this.neuter = neuter;
        this.reason = reason;
        this.before = before;
        this.fave = fave;
        this.dislike = dislike;
        this.image = image;
    }
}
