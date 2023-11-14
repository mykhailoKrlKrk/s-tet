package com.example.backend.dto.master;

import com.example.backend.model.Qualification;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MasterDto {
    private Long id;
    private String name;
    private String lastName;
    private Qualification qualification;
    private String coverImage;
}
