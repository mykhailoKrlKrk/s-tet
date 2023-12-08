package com.example.backend.controller;

import com.example.backend.dto.category.CategoryDto;
import com.example.backend.dto.category.CategoryRequestDto;
import com.example.backend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Service management", description = "Endpoints for services")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000",
        "http://s-tet.byethost12.com/"},
        methods = {
                RequestMethod.GET,
                RequestMethod.DELETE,
                RequestMethod.PUT,
                RequestMethod.POST
        })
@RequestMapping(value = "/services")
public class ServiceController {
    private static final Logger logger = LogManager.getLogger(ServiceController.class);
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create service", description = "Create new service in the DB")
    public CategoryDto createService(@RequestBody @Valid CategoryRequestDto requestDto) {
        logger.info("method createService is called with params: name - " + requestDto.getName());
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
    public void info(@PathVariable Long id) {
        logger.debug("method delete service is called with id: " + id);
        categoryService.deleteById(id);
    }
}
