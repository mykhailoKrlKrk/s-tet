package com.example.backend.dto.comment;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String fullName;
    private String description;
}
