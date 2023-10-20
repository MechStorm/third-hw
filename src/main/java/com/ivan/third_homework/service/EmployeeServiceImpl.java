package com.ivan.third_homework.service;

import com.ivan.third_homework.dto.EmployeeDTO;
import com.ivan.third_homework.dto.EmployeeDTONew;
import com.ivan.third_homework.entity.Department;
import com.ivan.third_homework.entity.Employees;
import com.ivan.third_homework.entity.Hobbies;
import com.ivan.third_homework.mapper.EmployeeMapper;
import com.ivan.third_homework.repository.DepartmentRepository;
import com.ivan.third_homework.repository.EmployeeRepository;
import com.ivan.third_homework.repository.HobbiesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;

    HobbiesRepository hobbiesRepository;
    EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, HobbiesRepository hobbiesRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.hobbiesRepository = hobbiesRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        List<Employees> employees = employeeRepository.findAll();
        return employees.stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getByID(Long id) {
        return employeeMapper.toDto(employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found!")));
    }

    @Override
    @Transactional
    public EmployeeDTO create(EmployeeDTONew employeeDTONew) {
        Optional<Department> department = departmentRepository.findById(employeeDTONew.getDepartmentId());
        if (department.isEmpty()) {
            throw new EntityNotFoundException("Department with this id not found");
        }
        Employees employees = employeeMapper.toEntity(employeeDTONew);
        return employeeMapper.toDto(employeeRepository.saveAndFlush(employees));
    }

    @Override
    @Transactional
    public EmployeeDTO update(Long id, EmployeeDTONew employeeDTONew) {
        Employees employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found!"));
        if (employeeDTONew.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeeDTONew.getDepartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Department id not found!"));
            employee.setDepartment(department);
        }

        Employees updateEmployee = employeeMapper.updateToEntity(employeeDTONew, employee);
        return employeeMapper.toDto(employeeRepository.saveAndFlush(updateEmployee));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void setHobbyToEmployee(Long empID, Long hobbyID) {
        Employees employee = employeeRepository.findById(empID).orElseThrow(() -> new EntityNotFoundException("Employee not found!"));
        Hobbies hobby = hobbiesRepository.findById(hobbyID).orElseThrow(() -> new EntityNotFoundException("Hobby not found!"));

        employee.getHobbies().add(hobby);
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public List<EmployeeDTO> findByHobbyId(Long hobbyID) {
        List<Employees> employees = employeeRepository.findByHobbyId(hobbyID);
        return employees.stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }
}
