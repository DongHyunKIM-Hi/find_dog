package com.example.find_dog.repository;

import com.example.find_dog.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//게시글 아이디로 해당 게시글의 댓글 찾아오기
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(Long article_id);

}
