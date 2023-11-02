package com.example.backend.dto.comment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequestDto {
    @NotNull
    private String fullName;
    @NotNull
    private String description;
    @NotNull
    @Min(0)
    private Integer rating;
}
