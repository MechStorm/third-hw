package com.ivan.third_homework.service;

import com.ivan.third_homework.dto.DepartmentDTO;
import com.ivan.third_homework.dto.DepartmentDTONew;
import com.ivan.third_homework.entity.Department;
import com.ivan.third_homework.mapper.DepartmentMapper;
import com.ivan.third_homework.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = mapper;
    }

    @Override
    public List<DepartmentDTO> getAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(departmentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO getByID(Long id) {
        return null;
    }

    @Override
    @Transactional
    public DepartmentDTO create(DepartmentDTONew departmentDTONew) {
        Department department = departmentMapper.toEntity(departmentDTONew);
        return departmentMapper.toDto(departmentRepository.saveAndFlush(department));
    }

    @Override
    public DepartmentDTO update(Long id, DepartmentDTONew departmentDTONew) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
