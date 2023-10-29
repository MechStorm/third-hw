package service;

import com.ivan.third_homework.dto.HobbyDTO;
import com.ivan.third_homework.dto.HobbyDTONew;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void getHobbyByIDTest() {
        Long hobbyID = 1L;
        Hobbies hobby = new Hobbies(hobbyID, "Art");
        HobbyDTO hobbyDTO = new HobbyDTO(hobbyID, "Art");

        when(hobbiesRepository.findById(hobbyID)).thenReturn(Optional.of(hobby));
        when(hobbyMapper.toDto(hobby)).thenReturn(hobbyDTO);

        HobbyDTO actualHobbyDTO = hobbyService.getByID(hobbyID);

        assertEquals(hobbyDTO, actualHobbyDTO);

        verify(hobbiesRepository, times(1)).findById(hobbyID);
        verify(hobbyMapper, times(1)).toDto(hobby);
    }

    @Test
    void createHobbyTest() {
        Long hobbyID = 1L;
        HobbyDTONew hobbyDTONew = new HobbyDTONew("Art");
        Hobbies hobby = new Hobbies(hobbyID, "Art");
        HobbyDTO hobbyDTO = new HobbyDTO(hobbyID, "Art");

        when(hobbyMapper.toEntity(hobbyDTONew)).thenReturn(hobby);
        when(hobbiesRepository.saveAndFlush(hobby)).thenReturn(hobby);
        when(hobbyMapper.toDto(any())).thenReturn(hobbyDTO);

        HobbyDTO actualHobbyDTO = hobbyService.create(hobbyDTONew);

        assertNotNull(actualHobbyDTO);
        assertEquals(1L, actualHobbyDTO.getId());
        assertEquals("Art", hobbyDTO.getName());

        verify(hobbyMapper, times(1)).toEntity(hobbyDTONew);
        verify(hobbiesRepository, times(1)).saveAndFlush(hobby);
        verify(hobbyMapper, times(1)).toDto(hobby);
    }

    @Test
    void updateHobbyTest() {
        Long hobbyID = 1L;
        HobbyDTONew hobbyDTONew = new HobbyDTONew("Art");
        Hobbies hobby = new Hobbies(hobbyID, "Art");
        Hobbies updatedHobby = new Hobbies(hobbyID, "Hiking");
        HobbyDTO expectedHobbyDTO = new HobbyDTO(hobbyID, "Hiking");

        when(hobbiesRepository.findById(hobbyID)).thenReturn(Optional.of(hobby));
        when(hobbyMapper.updateHobbyDtoToEntity(hobbyDTONew, hobby)).thenReturn(updatedHobby);
        when(hobbiesRepository.saveAndFlush(updatedHobby)).thenReturn(updatedHobby);
        when(hobbyMapper.toDto(any(Hobbies.class))).thenReturn(expectedHobbyDTO);

        HobbyDTO actualHobbyDTO = hobbyService.update(hobbyID, hobbyDTONew);

        assertNotNull(actualHobbyDTO);
        assertEquals(1L, actualHobbyDTO.getId());
        assertEquals("Hiking", actualHobbyDTO.getName());

        verify(hobbiesRepository, times(1)).findById(hobbyID);
        verify(hobbyMapper, times(1)).updateHobbyDtoToEntity(hobbyDTONew, hobby);
        verify(hobbiesRepository, times(1)).saveAndFlush(updatedHobby);
        verify(hobbyMapper, times(1)).toDto(updatedHobby);
    }

    @Test
    void deleteHobbyTest() {
        Long hobbyID = 1L;

        hobbyService.delete(hobbyID);

        verify(hobbiesRepository, times(1)).deleteById(hobbyID);
    }
}
