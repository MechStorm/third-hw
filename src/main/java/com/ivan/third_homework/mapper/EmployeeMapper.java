package com.ivan.third_homework.mapper;

import com.ivan.third_homework.dto.EmployeeDTO;
import com.ivan.third_homework.entity.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "department.id", target = "departmentId")
    EmployeeDTO toDto(Employees employees);
}
