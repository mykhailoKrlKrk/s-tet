package com.example.backend.mapper;

import com.example.backend.config.MapperConfig;
import com.example.backend.dto.category.CategoryDto;
import com.example.backend.dto.category.CategoryRequestDto;
import com.example.backend.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryDto toDto(Category service);

    Category toModel(CategoryRequestDto requestDto);
}
