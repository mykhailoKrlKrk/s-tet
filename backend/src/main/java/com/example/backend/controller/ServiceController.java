package com.example.backend.controller;

import com.example.backend.dto.category.CategoryDto;
import com.example.backend.dto.category.CategoryRequestDto;
import com.example.backend.dto.service.ServiceDto;
import com.example.backend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Service management", description = "Endpoints for services")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/services")
public class ServiceController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create service", description = "Create new service in the DB")
    public CategoryDto createService(@RequestBody @Valid CategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @GetMapping
    @Operation(summary = "Get all services",
            description = "Get list of all available services")
    public List<CategoryDto> getAll(Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete service", description = "Delete service by specific id")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
