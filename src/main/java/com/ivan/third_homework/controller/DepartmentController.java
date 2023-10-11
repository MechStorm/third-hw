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

    @PostMapping
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTONew departmentDTONew) {
        System.out.println(departmentDTONew.getName());
        System.out.println(departmentDTONew.getPhoneNumber());
        System.out.println(departmentDTONew.getEmail());
        System.out.println(departmentDTONew.getYearWorks());
        DepartmentDTO department = departmentService.create(departmentDTONew);
        return department;
    }
}
