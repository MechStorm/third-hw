package com.ivan.third_homework.controller;

import com.ivan.third_homework.dto.DepartmentDTO;
import com.ivan.third_homework.dto.DepartmentDTONew;
import com.ivan.third_homework.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAll();
        return departments;
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentByID(@PathVariable Long id) {
        DepartmentDTO department = departmentService.getByID(id);
        return department;
    }

    @PostMapping
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTONew departmentDTONew) {
        DepartmentDTO department = departmentService.create(departmentDTONew);
        return department;
    }

    @PutMapping("/{id}")
    public DepartmentDTO updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTONew departmentDTONew) {
        DepartmentDTO department = departmentService.update(id, departmentDTONew);
        return department;
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return "Success delete department with id " + id;
    }
}
