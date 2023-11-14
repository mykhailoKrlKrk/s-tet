package com.example.backend.dto.master;

import com.example.backend.model.Qualification;
import com.example.backend.validation.Formatter;
import com.example.backend.validation.PhoneNumber;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MasterRequestDto {

    @NotEmpty
    @Formatter
    private String name;

    @NotEmpty
    @Formatter
    private String lastName;

    @NotNull
    private Qualification qualification;

    @NotNull
    @Formatter
    private String coverImage;

    @NotEmpty
    @PhoneNumber
    private String phoneNumber;

    @NotNull
    private Long serviceId;
}
