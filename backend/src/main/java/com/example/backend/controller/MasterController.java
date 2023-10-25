package com.example.backend.controller;

import com.example.backend.dto.master.MasterDto;
import com.example.backend.service.MasterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Masters management", description = "Endpoints for masters")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/masters")
public class MasterController {

    private final MasterService masterService;

    @GetMapping
    @Operation(summary = "Get all masters",
            description = "Get list of all available masters")
    @ResponseStatus(HttpStatus.OK)
    public List<MasterDto> getAll(Pageable pageable) {
        return masterService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get master by id", description = "Get master by specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully find master by id"),
            @ApiResponse(responseCode = "404", description
                    = "Not found - master with this id is not exist")
    })
    public MasterDto findById(@PathVariable Long id) {
        return masterService.getById(id);
    }
}
