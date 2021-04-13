package com.example.find_dog.repository;

import com.example.find_dog.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(Long article_id);

}
