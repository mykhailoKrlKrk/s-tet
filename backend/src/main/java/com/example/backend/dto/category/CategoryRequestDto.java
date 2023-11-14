package com.example.backend.dto.category;

import com.example.backend.validation.Formatter;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryRequestDto {
    @NotEmpty
    @Formatter
    private String name;
}
