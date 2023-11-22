package com.example.backend.dto.comment;

import com.example.backend.validation.Formatter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CommentRequestDto {
    @NotEmpty
    @NotNull
    @Length(min = 3, max = 25)
    @Formatter
    private String fullName;
    @NotEmpty
    @NotNull
    @Formatter
    private String description;
}
