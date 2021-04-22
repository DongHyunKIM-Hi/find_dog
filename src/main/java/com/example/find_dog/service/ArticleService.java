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

//해당 게시글 정보를 업데이트 하기 위해 아이디로 게시글 찾아오는 코드
    @Transactional
    public List<String> update(Long id, ArticleRequestDto requestDto){
        Article article = articleRepository.findById(id).orElseThrow(
                () ->new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        String likeNickname = requestDto.getNickname();
        requestDto.getLikeId().add(likeNickname);
        article.update(requestDto);
        return requestDto.getLikeId();
    }


}
