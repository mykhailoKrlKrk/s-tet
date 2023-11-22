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
import org.hibernate.validator.constraints.Length;

@Data
@Accessors(chain = true)
public class OrderRequestDto {

    @NotEmpty
    @Formatter
    @Length(min = 3, max = 25)
    private String clientName;
    @NotEmpty
    @PhoneNumber
    private String phoneNumber;
    @NotNull
    @Min(200)
    private BigDecimal orderTotal;
    @NotNull
    private Long masterId;
    @NotNull
    private List<Long> servicesId;

    @Formatter
    @Length(min = 5, max = 500)
    private String comment;
}

