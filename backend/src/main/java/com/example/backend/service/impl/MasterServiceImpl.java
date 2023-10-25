package com.example.backend.service.impl;

import com.example.backend.dto.master.MasterDto;
import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.mapper.MasterMapper;
import com.example.backend.model.Master;
import com.example.backend.repository.MasterRepository;
import com.example.backend.service.MasterService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;
    private final MasterMapper masterMapper;

    @Override
    public List<MasterDto> getAll(Pageable pageable) {
        return masterRepository.findAll(pageable).stream()
                .map(masterMapper::toDto)
                .toList();
    }

    @Override
    public MasterDto getById(Long id) {
        Master master = masterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Master with id: "
                        + id + " is not exist"));
        return masterMapper.toDto(master);
    }
}
