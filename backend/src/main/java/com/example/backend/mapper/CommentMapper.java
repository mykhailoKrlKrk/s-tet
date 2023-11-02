package com.example.backend.mapper;

import com.example.backend.config.MapperConfig;
import com.example.backend.dto.comment.CommentDto;
import com.example.backend.dto.comment.CommentRequestDto;
import com.example.backend.model.Comment;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CommentMapper {
    CommentDto toDto(Comment comment);

    Comment toModel(CommentRequestDto requestDto);
}
