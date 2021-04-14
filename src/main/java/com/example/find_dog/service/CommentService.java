package com.example.find_dog.service;

import com.example.find_dog.model.entity.Comment;
import com.example.find_dog.model.dto.CommentRequestDto;
import com.example.find_dog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, CommentRequestDto requestDto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()->new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        comment.update(requestDto);
        return comment.getId();
    }
}
