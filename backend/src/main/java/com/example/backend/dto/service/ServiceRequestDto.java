package com.example.backend.dto.service;

import com.example.backend.validation.Formatter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

@Data
@Accessors(chain = true)
public class ServiceRequestDto {
    @NotEmpty
    @Length(min = 3, max = 25)
    @Formatter
    private String name;

    @NotNull
    @Min(200)
    private BigDecimal masterPrice;
    @NotNull
    @Min(200)
    private BigDecimal headMasterPrice;

    @NotNull
    private Long categoryId;
}
