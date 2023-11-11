package com.example.backend.dto.order;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetOrderSumRequestDto {
    @NotEmpty
    private Long masterId;
    @NotEmpty
    private List<Long> servicesId;
}
