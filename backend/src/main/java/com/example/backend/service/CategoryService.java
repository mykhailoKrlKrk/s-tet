package com.example.backend.service;

import com.example.backend.dto.category.CategoryDto;
import com.example.backend.dto.category.CategoryRequestDto;
import com.example.backend.model.Category;

public interface CategoryService {
    CategoryDto save(CategoryRequestDto requestDto);

    Category findById(Long id);

    void deleteById(Long id);
}
