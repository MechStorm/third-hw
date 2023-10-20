package com.ivan.third_homework.service;

import com.ivan.third_homework.dto.EmployeeDTO;
import com.ivan.third_homework.dto.EmployeeDTONew;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAll();

    EmployeeDTO getByID(Long id);

    EmployeeDTO create(EmployeeDTONew employeeDTONew);

    EmployeeDTO update(Long id, EmployeeDTONew employeeDTONew);

    void delete(Long id);

    void setHobbyToEmployee(Long empID, Long hobbyID);

    List<EmployeeDTO> findByHobbyId(Long hobbyID);
}
