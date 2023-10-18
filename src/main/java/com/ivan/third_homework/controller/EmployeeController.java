package com.ivan.third_homework.controller;

import com.ivan.third_homework.dto.EmployeeDTO;
import com.ivan.third_homework.dto.EmployeeDTONew;
import com.ivan.third_homework.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeByID(@PathVariable Long id) {
        return employeeService.getByID(id);
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTONew employeeDTONew) {
        EmployeeDTO employee = employeeService.create(employeeDTONew);
        return employee;
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTONew employeeDTONew) {
        EmployeeDTO employee = employeeService.update(id, employeeDTONew);
        return employee;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
