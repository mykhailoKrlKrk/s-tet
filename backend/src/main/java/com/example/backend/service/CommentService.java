package com.example.backend.service;

import com.example.backend.dto.comment.CommentDto;
import com.example.backend.dto.comment.CommentRequestDto;
import java.util.List;

public interface CommentService {

    CommentDto create(CommentRequestDto requestDto, String category);

    List<CommentDto> getAllComments();

    List<CommentDto> getCommentsByCategory(String category);

    void delete(Long id);
}
