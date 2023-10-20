package com.ivan.third_homework.mapper;

import com.ivan.third_homework.dto.HobbyDTO;
import com.ivan.third_homework.dto.HobbyDTONew;
import com.ivan.third_homework.entity.Hobbies;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HobbyMapper {

    Hobbies toEntity(HobbyDTONew hobbyDTONew);

    Hobbies updateHobbyDtoToEntity(HobbyDTONew hobbyDTONew, @MappingTarget Hobbies hobbies);

    HobbyDTO toDto(Hobbies hobbies);

}
