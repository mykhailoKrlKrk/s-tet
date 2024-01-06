package com.example.backend.dto.comment;

import com.example.backend.validation.Formatter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Data
@Accessors(chain = true)
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
