package com.example.backend.service;

import com.example.backend.dto.category.CategoryDto;
import com.example.backend.dto.category.CategoryRequestDto;

public interface CategoryService {
    CategoryDto save(CategoryRequestDto requestDto);

    CategoryDto findById(Long id);

    void deleteById(Long id);
}
