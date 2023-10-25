package com.example.backend.dto.master;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MasterDto {
    private Long id;
    private String name;
    private String lastName;
    private String qualification;
    private String description;
    private String coverImage;
}
