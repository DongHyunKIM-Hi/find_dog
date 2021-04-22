package com.example.find_dog.repository;

import com.example.find_dog.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    //최신순으로 게시글 정렬
    List<Article> findAllByOrderByModifiedAt();

//    List<String> findAllByUsername();
}
