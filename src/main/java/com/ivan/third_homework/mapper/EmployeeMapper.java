package com.ivan.third_homework.mapper;

import com.ivan.third_homework.dto.EmployeeDTO;
import com.ivan.third_homework.dto.EmployeeDTONew;
import com.ivan.third_homework.entity.Employees;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source = "departmentId", target = "department.id")
    Employees toEntity(EmployeeDTONew employeeDTONew);

    Employees updateToEntity(EmployeeDTONew employeeDTONew, @MappingTarget Employees employees);

    @Mapping(source = "department.id", target = "departmentId")
    EmployeeDTO toDto(Employees employees);
}
