package com.example.find_dog.controller;

import com.example.find_dog.config.WebConfig;
import com.example.find_dog.model.dto.CommentRequestDto;
import com.example.find_dog.model.entity.Article;
import com.example.find_dog.model.entity.Comment;
import com.example.find_dog.repository.ArticleRepository;
import com.example.find_dog.repository.CommentRepository;
import com.example.find_dog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//cors
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    //해당 게시글에 따른 댓글 조회
    @GetMapping("/api/comment/{article_id}")
    public List<Comment> getComment(@PathVariable Long article_id){
        return commentRepository.findAllByArticleId(article_id);
    }

    //해당 게시글에 댓글 작성
    @PostMapping("/api/comment/{article_id}")
    public Comment creatComment(@PathVariable Long article_id, @RequestBody CommentRequestDto requestDto){
        Article article = articleRepository.findById(article_id).get();
        Comment comment = new Comment(requestDto, article);
        return commentRepository.save(comment);

    }

    //댓글 수정
    @PutMapping("/api/comment/{comment_id}")
    public Long updateComment(@PathVariable Long comment_id, @RequestBody CommentRequestDto requestDto){
        return commentService.update(comment_id, requestDto);
    }

    //댓글 삭제
    @DeleteMapping("/api/comment/{comment_id}")
    public Long deleteComment(@PathVariable Long comment_id){
        commentRepository.deleteById(comment_id);
        return comment_id;
    }

}
