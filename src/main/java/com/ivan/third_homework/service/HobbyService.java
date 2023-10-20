package com.ivan.third_homework.service;

import com.ivan.third_homework.dto.HobbyDTO;
import com.ivan.third_homework.dto.HobbyDTONew;

import java.util.List;

public interface HobbyService {
    List<HobbyDTO> getAll();

    HobbyDTO getByID(Long id);

    HobbyDTO create(HobbyDTONew hobbyDTONew);

    HobbyDTO update(Long id, HobbyDTONew hobbyDTONew);

    void delete(Long id);
}
