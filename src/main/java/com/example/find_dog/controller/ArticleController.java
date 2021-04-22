package com.example.find_dog.controller;

import com.example.find_dog.model.dto.ArticleRequestDto;
import com.example.find_dog.model.entity.Article;
import com.example.find_dog.repository.ArticleRepository;
import com.example.find_dog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    //게시글 전체 조회
    @ResponseBody
    @GetMapping( "/api/article")
    public List<Article> getArticle(){

        return articleRepository.findAllByOrderByModifiedAt();
    }

    //게시글 작성하기
    @ResponseBody
    @PostMapping("/api/article")
    public Article creatArticle(@RequestBody ArticleRequestDto requestDto){
        Article article = new Article(requestDto);
        return articleRepository.save(article);
    }

    //게시글 수정 및 좋아요 수정 api
    @PutMapping( "/api/article/{article_id}")
    public  List<String>  updateArticle(@PathVariable Long article_id, @RequestBody ArticleRequestDto requestDto){
        return articleService.update(article_id, requestDto);
    }

    //게시글 삭제하기
    @DeleteMapping( "/api/article/{article_id}")
    public Long deleteArticle(@PathVariable Long article_id) {
        articleRepository.deleteById(article_id);
        return article_id;
    }

}
