package com.example.backend.dto.comment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Setter
@Getter
@Accessors(chain = true)
public class CommentDto {
    private Long id;
    private String fullName;
    private String description;
}
