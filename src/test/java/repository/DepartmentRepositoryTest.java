package repository;

import com.ivan.third_homework.entity.Department;
import com.ivan.third_homework.repository.DepartmentRepository;
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
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void getByIDAndSaveFlushDepartmentTest() {
        Department department = new Department();
        department.setName("IT");
        department.setPhoneNumber(123);
        department.setEmail("supercompany@gmail.com");
        department.setYearWorks(5);

        departmentRepository.saveAndFlush(department);
        Long departmentID = department.getId();
        Optional<Department> gettingDepartment = departmentRepository.findById(departmentID);
        assertTrue(gettingDepartment.isPresent());
        assertEquals("IT", gettingDepartment.get().getName());
    }

    @Test
    void saveAndFindAllDepartments() {
        Department department = new Department();
        department.setName("IT");
        department.setPhoneNumber(123);
        department.setEmail("supercompany@gmail.com");
        department.setYearWorks(5);

        Department department2 = new Department();
        department2.setName("Sales");
        department2.setPhoneNumber(456);
        department2.setEmail("supercompany2@gmail.com");
        department2.setYearWorks(10);

        departmentRepository.saveAll(List.of(department, department2));

        List<Department> departments = departmentRepository.findAll();
        assertEquals(2, departments.size());
        assertEquals("IT", departments.get(0).getName());
        assertEquals(456, departments.get(1).getPhoneNumber());
    }

    @Test
    void deleteDepartmentTest() {
        Department department = new Department();
        department.setName("IT");
        department.setPhoneNumber(123);
        department.setEmail("supercompany@gmail.com");
        department.setYearWorks(5);

        departmentRepository.saveAndFlush(department);

        assertTrue(departmentRepository.existsById(department.getId()));

        departmentRepository.deleteById(department.getId());

        assertFalse(departmentRepository.existsById(department.getId()));
    }

    @Test
    void updateDepartmentTest() {
        Department department = new Department();
        department.setName("IT");
        department.setPhoneNumber(123);
        department.setEmail("supercompany@gmail.com");
        department.setYearWorks(5);

        departmentRepository.saveAndFlush(department);

        Long departmentID = department.getId();
        Department updatedDepartment = departmentRepository.findById(departmentID).orElse(null);
        assertNotNull(updatedDepartment);

        updatedDepartment.setPhoneNumber(12345);
        departmentRepository.saveAndFlush(updatedDepartment);

        Department newDepartment = departmentRepository.findById(departmentID).orElse(null);
        assertNotNull(newDepartment);
        assertEquals(12345, newDepartment.getPhoneNumber());
    }
}
