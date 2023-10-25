package com.example.backend.service;

import com.example.backend.dto.master.MasterDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface MasterService {
    List<MasterDto> getAll(Pageable pageable);

    MasterDto getById(Long id);
}
