package service;

import com.ivan.third_homework.dto.HobbyDTO;
import com.ivan.third_homework.entity.Hobbies;
import com.ivan.third_homework.mapper.HobbyMapper;
import com.ivan.third_homework.repository.HobbiesRepository;
import com.ivan.third_homework.service.HobbyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HobbyServiceTest {
    @InjectMocks
    HobbyServiceImpl hobbyService;

    @Mock
    HobbiesRepository hobbiesRepository;

    @Mock
    HobbyMapper hobbyMapper;

    @Test
    void getAllHobbiesTest() {
        List<Hobbies> hobbies = new ArrayList<>();
        Hobbies hobby = new Hobbies(1L, "Art");
        Hobbies hobby2 = new Hobbies(2L, "Videogames");
        hobbies.addAll(List.of(hobby, hobby2));

        when(hobbiesRepository.findAll()).thenReturn(hobbies);

        List<HobbyDTO> expectedHobbyDTO = new ArrayList<>();
        expectedHobbyDTO.add(new HobbyDTO(1L, "Art"));
        expectedHobbyDTO.add(new HobbyDTO(2L, "Videogames"));

        when(hobbyMapper.toDto(any())).thenAnswer(answer -> {
            Hobbies hobbyAns = answer.getArgument(0);
            return new HobbyDTO(hobbyAns.getId(), hobbyAns.getName());
        });

        List<HobbyDTO> actualHobbyDTO = hobbyService.getAll();
        assertIterableEquals(expectedHobbyDTO, actualHobbyDTO);
    }
}
