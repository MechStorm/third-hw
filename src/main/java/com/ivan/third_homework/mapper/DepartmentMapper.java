package com.ivan.third_homework.mapper;

import com.ivan.third_homework.dto.DepartmentDTO;
import com.ivan.third_homework.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDTO toDto(Department department);
}
