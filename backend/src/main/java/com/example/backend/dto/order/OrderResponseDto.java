package com.example.backend.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderResponseDto {
    private Long id;
    private String clientName;
    private BigDecimal orderTotal;
    private LocalDateTime orderDate;
    private String comment;
}
