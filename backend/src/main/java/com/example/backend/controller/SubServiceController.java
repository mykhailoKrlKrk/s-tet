package com.example.backend.controller;

import com.example.backend.dto.service.ServiceDto;
import com.example.backend.dto.service.ServiceRequestDto;
import com.example.backend.service.ServicesService;
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

@Tag(name = "Subservices management", description = "Endpoints for subservices")
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
@RequestMapping(value = "/subservices")
public class SubServiceController {
    private static final Logger logger = LogManager.getLogger(SubServiceController.class);
    private final ServicesService servicesService;

    @GetMapping("/{category}")
    @Operation(summary = "Get all subservices by service",
            description = "Get subservices that specific service support")
    public List<ServiceDto> getAllSubServicesByService(@PathVariable String category) {
        logger.debug("method getAllSubServicesByService is called with category: " + category);
        return servicesService.getServicesByCategory(category);
    }

    @GetMapping
    @Operation(summary = "Get all subservices",
            description = "Get list of all available subservices")
    public List<ServiceDto> getAll(Pageable pageable) {
        return servicesService.getAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create subservice", description = "Create new subservice in the DB")
    public ServiceDto createSubService(@RequestBody @Valid ServiceRequestDto requestDto) {
        logger.info("method createSubService is called with params: name - "
                + requestDto.getName() + ", serviceId - " + requestDto.getCategoryId());
        return servicesService.save(requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete subservice", description = "Delete subservice by specific id")
    public void delete(@PathVariable Long id) {
        logger.info("method delete subService is called with id: " + id);
        servicesService.deleteById(id);
    }
}
