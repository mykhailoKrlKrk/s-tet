package com.example.backend.dto.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServiceRequestDto {
    @NotEmpty
    private String name;
    @NotNull
    @Min(0)
    private BigDecimal masterPrice;
    @NotNull
    @Min(0)
    private BigDecimal headMasterPrice;

    @NotNull
    private Long categoryId;
}
