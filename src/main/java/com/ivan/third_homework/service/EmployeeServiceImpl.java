package com.ivan.third_homework.service;

import com.ivan.third_homework.dto.EmployeeDTO;
import com.ivan.third_homework.entity.Employees;
import com.ivan.third_homework.mapper.EmployeeMapper;
import com.ivan.third_homework.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        List<Employees> employees = employeeRepository.findAll();
        return employees.stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }
}
