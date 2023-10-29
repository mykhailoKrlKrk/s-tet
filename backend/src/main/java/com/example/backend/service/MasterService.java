package com.example.backend.service;

import com.example.backend.dto.master.MasterDto;
import com.example.backend.dto.master.MasterRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface MasterService {
    List<MasterDto> getAll(Pageable pageable);

    MasterDto getById(Long id);

    List<MasterDto> getMastersByService(String service);

    List<MasterDto> getMastersByCategory(String category);

    MasterDto createMaster(MasterRequestDto requestDto);

    MasterDto findById(Long id);

    void delete(Long id);
}
