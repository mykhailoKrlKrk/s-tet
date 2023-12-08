package com.example.backend.controller;

import com.example.backend.dto.master.MasterDto;
import com.example.backend.dto.master.MasterRequestDto;
import com.example.backend.service.MasterService;
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

@Tag(name = "Masters management", description = "Endpoints for masters")
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
@RequestMapping(value = "/masters")
public class MasterController {

    public static final Logger logger = LogManager.getLogger(MasterController.class);
    private final MasterService masterService;

    @GetMapping
    @Operation(summary = "Get all masters",
            description = "Get list of all available masters")
    @ResponseStatus(HttpStatus.OK)
    public List<MasterDto> getAll(Pageable pageable) {
        return masterService.getAll(pageable);
    }

    @GetMapping("/{category}")
    @Operation(summary = "Get masters by category",
            description = "Get list of masters by category")
    public List<MasterDto> getMastersByCategory(@PathVariable String category) {
        logger.debug("method getMastersByCategory is called with category: " + category);
        return masterService.getMastersByCategory(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create master", description = "Create new master in the DB")
    public MasterDto createMaster(@RequestBody @Valid MasterRequestDto requestDto) {
        logger.info("method createMaster is called with params: name - "
                + requestDto.getFullName(), ",serviceId - "
                + requestDto.getServiceId());
        return masterService.createMaster(requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete master", description = "Delete master by specific id")
    public void delete(@PathVariable Long id) {
        logger.info("method delete master is called with id: " + id);
        masterService.delete(id);
    }
}
