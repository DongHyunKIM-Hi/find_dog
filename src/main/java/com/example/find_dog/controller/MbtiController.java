package com.example.find_dog.controller;

import com.example.find_dog.model.dto.ResultDto;
import com.example.find_dog.model.entity.Mbti_test;
import com.example.find_dog.model.entity.Mbti_type;
import com.example.find_dog.repository.Mbti_testRepository;
import com.example.find_dog.repository.Mbti_typeRepository;
import com.example.find_dog.service.MbtiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MbtiController {
    private final Mbti_testRepository mbti_testRepository;
    private final Mbti_typeRepository mbti_typeRepository;
    private final MbtiService mbtiService;

    // 설문지 받아오기
    @GetMapping("/api/mbti/start")
    public List<Mbti_test> test_list() {
        return mbti_testRepository.findAll();
    }

    // 설문 결과 반환하기
    @PostMapping("/api/mbti/result")
    public Mbti_type result(@RequestBody ResultDto resultDto) {
        Mbti_type result = mbtiService.result(resultDto);
        return result;
    }
// 초반에 한번만 디비에 값 세팅해주고 더 이상 실행되지 않게 주석 처리
//    @GetMapping("/api/mbti/dbSetting")
//    public void make_db(){
//        mbtiService.createDB1();
//        mbtiService.createDB2();
//    }
}
