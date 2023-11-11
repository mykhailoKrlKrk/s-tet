package com.example.backend.controller;

import com.example.backend.dto.service.ServiceDto;
import com.example.backend.dto.service.ServiceRequestDto;
import com.example.backend.service.ServicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Subservices management", description = "Endpoints for subservices")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/subservices")
public class ServiceController {
    private final ServicesService servicesService;

    @GetMapping("/{category}")
    @Operation(summary = "Get all services by category",
            description = "Get services that specific category support")
    public List<ServiceDto> getAllServicesByCategory(@PathVariable String category) {
        return servicesService.getServicesByCategory(category);
    }

    @GetMapping
    @Operation(summary = "Get all services",
            description = "Get list of all available services")
    public List<ServiceDto> getAll(Pageable pageable) {
        return servicesService.getAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create service", description = "Create new service in the DB")
    public ServiceDto createService(@RequestBody @Valid ServiceRequestDto requestDto) {
        return servicesService.save(requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete service", description = "Delete service by specific id")
    public void delete(@PathVariable Long id) {
        servicesService.deleteById(id);
    }
}
