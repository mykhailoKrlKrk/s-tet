package com.example.backend.service.impl;

import com.example.backend.dto.comment.CommentDto;
import com.example.backend.dto.comment.CommentRequestDto;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.mapper.CommentMapper;
import com.example.backend.model.Comment;
import com.example.backend.repository.CommentRepository;
import com.example.backend.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto create(CommentRequestDto requestDto) {
        Comment comment = commentMapper.toModel(requestDto);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(commentMapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find comment by id: " + id));
        commentRepository.deleteById(id);
    }
}
