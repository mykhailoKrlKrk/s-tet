package com.example.backend.service;

import com.example.backend.dto.comment.CommentDto;
import com.example.backend.dto.comment.CommentRequestDto;
import java.util.List;

public interface CommentService {

    CommentDto create(CommentRequestDto requestDto);

    List<CommentDto> getAllComments();

    void delete(Long id);
}
