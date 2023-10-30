package repository;

import com.ivan.third_homework.entity.Hobbies;
import com.ivan.third_homework.repository.HobbiesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(TestConfig.class)
@ExtendWith(MySQLExtension.class)
@Transactional
public class HobbiesRepositoryTest {

    @Autowired
    HobbiesRepository hobbiesRepository;

    @Test
    void getByIDAndSaveFlushHobbiesTest() {
        Hobbies hobby = new Hobbies();
        hobby.setName("Art");

        hobbiesRepository.saveAndFlush(hobby);
        Long hobbyID = hobby.getId();
        Optional<Hobbies> optHobby = hobbiesRepository.findById(hobbyID);
        assertNotNull(optHobby);
        assertTrue(optHobby.isPresent());
        assertEquals("Art", optHobby.get().getName());
    }

    @Test
    void saveAndFindAllHobbies() {
        Hobbies hobby = new Hobbies();
        hobby.setName("Art");

        Hobbies hobby2 = new Hobbies();
        hobby2.setName("Music");

        hobbiesRepository.saveAll(List.of(hobby, hobby2));

        List<Hobbies> hobbies = hobbiesRepository.findAll();
        assertEquals(2, hobbies.size());
        assertEquals("Art", hobbies.get(0).getName());
        assertEquals("Music", hobbies.get(1).getName());
    }

    @Test
    void deleteHobbyTest() {
        Hobbies hobby = new Hobbies();
        hobby.setName("Art");

        hobbiesRepository.saveAndFlush(hobby);

        assertTrue(hobbiesRepository.existsById(hobby.getId()));

        hobbiesRepository.deleteById(hobby.getId());

        assertFalse(hobbiesRepository.existsById(hobby.getId()));
    }

    @Test
    void updateHobbyTest() {
        Hobbies hobby = new Hobbies();
        hobby.setName("Art");

        hobbiesRepository.saveAndFlush(hobby);

        Long hobbyID = hobby.getId();
        Hobbies updatedHobby = hobbiesRepository.findById(hobbyID).orElse(null);
        assertNotNull(updatedHobby);

        updatedHobby.setName("Languages");
        hobbiesRepository.saveAndFlush(updatedHobby);

        Hobbies newHobby = hobbiesRepository.findById(hobbyID).orElse(null);
        assertNotNull(newHobby);
        assertEquals("Languages", newHobby.getName());
    }
}
