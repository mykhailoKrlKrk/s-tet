package com.example.backend.service;

import com.example.backend.dto.category.CategoryDto;
import com.example.backend.dto.category.CategoryRequestDto;
import com.example.backend.model.Category;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto save(CategoryRequestDto requestDto);

    List<CategoryDto> getAll(Pageable pageable);

    Category findById(Long id);

    void deleteById(Long id);
}
