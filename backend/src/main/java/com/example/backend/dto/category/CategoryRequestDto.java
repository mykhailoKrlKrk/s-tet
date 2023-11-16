package com.example.backend.dto.category;

import com.example.backend.validation.Formatter;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Data
@Accessors(chain = true)
public class CategoryRequestDto {
    @NotEmpty
    @Formatter
    @Length(max = 20)
    private String name;
}
