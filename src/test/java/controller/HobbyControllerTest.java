package controller;

import com.ivan.third_homework.controller.HobbyController;
import com.ivan.third_homework.dto.HobbyDTO;
import com.ivan.third_homework.dto.HobbyDTONew;
import com.ivan.third_homework.service.HobbyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HobbyControllerTest {
    @InjectMocks
    HobbyController hobbyController;

    @Mock
    HobbyService hobbyService;

    @Test
    void getAllHobbies() {
        HobbyDTO hobbyDTO1 = new HobbyDTO();
        HobbyDTO hobbyDTO2 = new HobbyDTO();

        List<HobbyDTO> hobbies = new ArrayList<>(List.of(hobbyDTO1, hobbyDTO2));
        when(hobbyService.getAll()).thenReturn(hobbies);

        List<HobbyDTO> response = hobbyController.getAllHobbies();

        assertEquals(hobbies, response);

        verify(hobbyService, times(1)).getAll();
    }

    @Test
    void getHobbyByID() {
        HobbyDTO hobby = new HobbyDTO();
        Long hobbyID = 1L;

        hobby.setId(hobbyID);
        hobby.setName("Art");

        when(hobbyService.getByID(hobbyID)).thenReturn(hobby);

        HobbyDTO response = hobbyController.getHobbyById(hobbyID);

        assertEquals(hobby, response);

        verify(hobbyService, times(1)).getByID(hobbyID);
    }

    @Test
    void createHobby() {
        HobbyDTONew hobbyDTONew = new HobbyDTONew();
        hobbyDTONew.setName("Art");

        HobbyDTO hobbyDTO = new HobbyDTO();
        hobbyDTO.setId(1L);
        hobbyDTO.setName(hobbyDTONew.getName());

        when(hobbyService.create(hobbyDTONew)).thenReturn(hobbyDTO);

        HobbyDTO response = hobbyController.createHobby(hobbyDTONew);

        assertEquals(hobbyDTO, response);

        verify(hobbyService, times(1)).create(hobbyDTONew);
    }

    @Test
    void updateHobby() {
        Long hobbyID = 1L;
        HobbyDTONew hobbyDTONew = new HobbyDTONew();
        hobbyDTONew.setName("Art");

        HobbyDTO hobby = new HobbyDTO();
        hobby.setId(hobbyID);
        hobby.setName(hobbyDTONew.getName());

        when(hobbyService.update(hobbyID, hobbyDTONew)).thenReturn(hobby);

        HobbyDTO response = hobbyController.updateHobby(hobbyID, hobbyDTONew);

        assertEquals(hobby, response);

        verify(hobbyService, times(1)).update(hobbyID, hobbyDTONew);
    }

    @Test
    void deleteHobby() {
        Long hobbyId = 1L;

        assertEquals("Success delete hobby with id " + hobbyId, hobbyController.deleteHobby(hobbyId));
    }
}
