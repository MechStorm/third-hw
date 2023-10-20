package com.ivan.third_homework.service;

import com.ivan.third_homework.dto.HobbyDTO;
import com.ivan.third_homework.dto.HobbyDTONew;
import com.ivan.third_homework.entity.Hobbies;
import com.ivan.third_homework.mapper.HobbyMapper;
import com.ivan.third_homework.repository.HobbiesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HobbyServiceImpl implements HobbyService {

    HobbiesRepository hobbiesRepository;
    HobbyMapper hobbyMapper;

    @Autowired
    public HobbyServiceImpl(HobbiesRepository hobbiesRepository, HobbyMapper hobbyMapper) {
        this.hobbiesRepository = hobbiesRepository;
        this.hobbyMapper = hobbyMapper;
    }

    @Override
    public List<HobbyDTO> getAll() {
        return hobbiesRepository.findAll().stream().map(hobbyMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public HobbyDTO getByID(Long id) {
        Hobbies hobby = hobbiesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hobby was not found!"));
        return hobbyMapper.toDto(hobby);
    }

    @Override
    @Transactional
    public HobbyDTO create(HobbyDTONew hobbyDTONew) {
        Hobbies hobby = hobbyMapper.toEntity(hobbyDTONew);
        return hobbyMapper.toDto(hobbiesRepository.saveAndFlush(hobby));
    }

    @Override
    @Transactional
    public HobbyDTO update(Long id, HobbyDTONew hobbyDTONew) {
        Hobbies hobby = hobbiesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hobby not found!"));
        Hobbies updatedHobby = hobbyMapper.updateHobbyDtoToEntity(hobbyDTONew, hobby);
        return hobbyMapper.toDto(hobbiesRepository.saveAndFlush(updatedHobby));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        hobbiesRepository.deleteById(id);
    }
}
