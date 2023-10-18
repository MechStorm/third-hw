package com.ivan.third_homework.mapper;

import com.ivan.third_homework.dto.DepartmentDTO;
import com.ivan.third_homework.dto.DepartmentDTONew;
import com.ivan.third_homework.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toEntity(DepartmentDTONew departmentDTONew);

    Department updateDtoToEntity(DepartmentDTONew departmentDTONew, @MappingTarget Department department);

    DepartmentDTO toDto(Department department);
}
