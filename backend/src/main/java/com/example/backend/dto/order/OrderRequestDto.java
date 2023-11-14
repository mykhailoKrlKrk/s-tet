package com.example.backend.dto.order;

import com.example.backend.validation.Formatter;
import com.example.backend.validation.PhoneNumber;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderRequestDto {

    @NotEmpty
    @Formatter
    private String clientName;
    @NotEmpty
    @PhoneNumber
    private String phoneNumber;
    @NotNull
    @Min(0)
    private BigDecimal orderTotal;
    @NotNull
    private Long masterId;
    @NotNull
    private List<Long> servicesId;

    @NotEmpty
    @Formatter
    private String comment;
}

