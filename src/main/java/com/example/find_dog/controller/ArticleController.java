package com.example.find_dog.controller;

import com.example.find_dog.config.WebConfig;
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

    @ResponseBody
    @GetMapping( "/api/article")
    public List<Article> getArticle(){

        return articleRepository.findAllByOrderByModifiedAt();
    }

    @ResponseBody
    @PostMapping("/api/article")
    public Article creatArticle(@RequestBody ArticleRequestDto requestDto){
        Article article = new Article(requestDto);
        return articleRepository.save(article);
    }

    @PutMapping( "/api/article/{article_id}")
    public Long updateArticle(@PathVariable Long article_id, @RequestBody ArticleRequestDto requestDto){
        return articleService.update(article_id, requestDto);
    }

    @DeleteMapping( "/api/article/{article_id}")
    public Long deleteArticle(@PathVariable Long article_id) {
        articleRepository.deleteById(article_id);
        return article_id;
    }

}
