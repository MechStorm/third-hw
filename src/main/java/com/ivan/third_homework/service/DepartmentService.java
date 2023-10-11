package com.ivan.third_homework.service;

import com.ivan.third_homework.dto.DepartmentDTO;
import com.ivan.third_homework.dto.DepartmentDTONew;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAll();
    DepartmentDTO getByID(Long id);
    DepartmentDTO create(DepartmentDTONew departmentDTONew);
    DepartmentDTO update(Long id, DepartmentDTONew departmentDTONew);

    void delete(Long id);
}
