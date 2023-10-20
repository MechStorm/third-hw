package com.ivan.third_homework.controller;

import com.ivan.third_homework.dto.HobbyDTO;
import com.ivan.third_homework.dto.HobbyDTONew;
import com.ivan.third_homework.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hobbies")
public class HobbyController {
    HobbyService hobbyService;

    @Autowired
    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @GetMapping
    public List<HobbyDTO> getAllHobbies() {
        return hobbyService.getAll();
    }

    @GetMapping("/{id}")
    public HobbyDTO getHobbyById(@PathVariable Long id) {
        return hobbyService.getByID(id);
    }

    @PostMapping
    public HobbyDTO createHobby(@RequestBody HobbyDTONew hobbyDTONew) {
        return hobbyService.create(hobbyDTONew);
    }
    @PutMapping("/{id}")
    public HobbyDTO updateHobby(@PathVariable Long id, @RequestBody HobbyDTONew hobbyDTONew) {
        return hobbyService.update(id, hobbyDTONew);
    }

    @DeleteMapping("/{id}")
    public void deleteHobby(@PathVariable Long id) {
        hobbyService.delete(id);
    }
}
