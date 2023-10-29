package com.example.backend.service;

import com.example.backend.dto.service.ServiceDto;
import com.example.backend.dto.service.ServiceRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface ServicesService {
    List<ServiceDto> getServicesByCategory(String category);

    ServiceDto save(ServiceRequestDto requestDto);

    List<ServiceDto> getAll(Pageable pageable);

    ServiceDto findById(Long id);

    void deleteById(Long id);
}
