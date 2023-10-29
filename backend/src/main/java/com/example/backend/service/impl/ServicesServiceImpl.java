package com.example.backend.service.impl;

import com.example.backend.dto.service.ServiceDto;
import com.example.backend.dto.service.ServiceRequestDto;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.mapper.ServiceMapper;
import com.example.backend.model.Service;
import com.example.backend.repository.ServiceRepository;
import com.example.backend.service.ServicesService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ServicesServiceImpl implements ServicesService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public List<ServiceDto> getServicesByCategory(String category) {
        return serviceRepository.getServicesByCategoryName(category).stream()
                .map(serviceMapper::toDto)
                .toList();
    }

    @Override
    public ServiceDto save(ServiceRequestDto requestDto) {
        Service book = serviceMapper.toModel(requestDto);
        return serviceMapper.toDto(serviceRepository.save(book));
    }

    @Override
    public List<ServiceDto> getAll(Pageable pageable) {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toDto)
                .toList();
    }

    @Override
    public ServiceDto findById(Long id) {
        return serviceMapper.toDto(serviceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find service by id: " + id)));
    }

    @Override
    public void deleteById(Long id) {
        serviceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find service by id: " + id));
        serviceRepository.deleteById(id);
    }
}
