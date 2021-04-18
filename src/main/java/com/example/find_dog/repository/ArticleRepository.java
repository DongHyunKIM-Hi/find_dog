package com.example.find_dog.repository;

import com.example.find_dog.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByModifiedAt();

//    List<String> findAllByUsername();
}
