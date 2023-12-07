package com.example.backend.dto.master;

import com.example.backend.model.Qualification;
import com.example.backend.validation.Formatter;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

@Data
@Accessors(chain = true)
public class MasterRequestDto {

    @NotEmpty
    @Length(min = 3, max = 25)
    @Formatter
    private String fullName;

    @NotNull
    private Qualification qualification;

    @NotNull
    @Formatter
    private String coverImage;

    @NotEmpty
    private String phoneNumber;

    @NotNull
    @NumberFormat
    private Long serviceId;
}
