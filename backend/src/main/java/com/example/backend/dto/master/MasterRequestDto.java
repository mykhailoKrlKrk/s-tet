package com.example.backend.dto.master;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MasterRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String qualification;
    @NotNull
    private String coverImage;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Long serviceId;
}
