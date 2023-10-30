package com.example.backend.dto.service;

import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServiceDto {
    private Long id;
    private String name;
    private BigDecimal masterPrice;
    private BigDecimal headMasterPrice;
}
