package com.example.find_dog.controller;

import com.example.find_dog.model.dto.ResultDto;
import com.example.find_dog.model.entity.Mbti_test;
import com.example.find_dog.model.entity.Mbti_type;
import com.example.find_dog.repository.Mbti_testRepository;
import com.example.find_dog.repository.Mbti_typeRepository;
import com.example.find_dog.service.MbtiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MbtiController {
    private final Mbti_testRepository mbti_testRepository;
    private final Mbti_typeRepository mbti_typeRepository;
    private final MbtiService mbtiService;


    @GetMapping("/api/mbti/start")
    public List<Mbti_test> test_list(){
        return mbti_testRepository.findAll();
    }

    @PostMapping("/api/mbti/result")
    public Mbti_type result(@RequestBody ResultDto resultDto){
        Mbti_type result = mbtiService.result(resultDto);
        return result;
    }

//    @GetMapping("/api/mbti/dbSetting")
//    public void make_db(){
//        mbtiService.createDB1();
//        mbtiService.createDB2();
//    }
}
