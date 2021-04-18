package com.example.find_dog.service;

import com.example.find_dog.model.dto.ArticleRequestDto;
import com.example.find_dog.model.entity.Article;
import com.example.find_dog.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

//    @Transactional
//    public Long update(Long id, ArticleRequestDto requestDto){
//        Article article = articleRepository.findById(id).orElseThrow(
//                () ->new NullPointerException("해당 게시글이 존재하지 않습니다.")
//        );
//        article.update(requestDto);
//        return article.getId();
//    }

    @Transactional
    public List<String> update(Long id, ArticleRequestDto requestDto){
        Article article = articleRepository.findById(id).orElseThrow(
                () ->new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        String likeUsername = requestDto.getUsername();
        requestDto.getLikeId().add(likeUsername);
        article.update(requestDto);
        return requestDto.getLikeId();
    }


}
