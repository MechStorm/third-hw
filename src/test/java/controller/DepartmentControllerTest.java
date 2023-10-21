package controller;

import com.ivan.third_homework.controller.DepartmentController;
import com.ivan.third_homework.dto.DepartmentDTO;
import com.ivan.third_homework.dto.DepartmentDTONew;
import com.ivan.third_homework.service.DepartmentService;
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
public class DepartmentControllerTest {
    @InjectMocks
    DepartmentController departmentController;
    @Mock
    DepartmentService departmentService;

    @Test
    void getAllDepartments() {
        DepartmentDTO departmentDTO1 = new DepartmentDTO();
        DepartmentDTO departmentDTO2 = new DepartmentDTO();

        List<DepartmentDTO> departments = new ArrayList<>(List.of(departmentDTO1, departmentDTO2));
        when(departmentService.getAll()).thenReturn(departments);

        List<DepartmentDTO> response = departmentController.getAllDepartments();

        assertEquals(departments, response);

        verify(departmentService, times(1)).getAll();
    }

    @Test
    void getDepartmentByID() {
        DepartmentDTO department = new DepartmentDTO();
        Long id = 1L;
        department.setId(id);
        department.setName("John");
        department.setEmail("johny123@gmail.com");
        department.setYearWorks(5);
        department.setPhoneNumber(911);

        when(departmentService.getByID(id)).thenReturn(department);

        DepartmentDTO response = departmentController.getDepartmentByID(id);

        assertEquals(department, response);

        verify(departmentService, times(1)).getByID(id);
    }

    @Test
    void createDepartment() {
        DepartmentDTONew departmentDTONew = new DepartmentDTONew();
        departmentDTONew.setName("John");
        departmentDTONew.setEmail("johny123@gmail.com");
        departmentDTONew.setYearWorks(5);
        departmentDTONew.setPhoneNumber(911);

        DepartmentDTO department = new DepartmentDTO();
        department.setId(1L);
        department.setName(departmentDTONew.getName());
        department.setEmail(departmentDTONew.getEmail());
        department.setYearWorks(departmentDTONew.getYearWorks());
        department.setPhoneNumber(departmentDTONew.getPhoneNumber());

        when(departmentService.create(departmentDTONew)).thenReturn(department);

        DepartmentDTO response = departmentController.createDepartment(departmentDTONew);

        assertEquals(department, response);

        verify(departmentService, times(1)).create(departmentDTONew);
    }

    @Test
    void updateDepartment() {
        Long departmentID = 1L;
        DepartmentDTONew departmentDTONew = new DepartmentDTONew();
        departmentDTONew.setName("John");
        departmentDTONew.setEmail("johny123@gmail.com");
        departmentDTONew.setYearWorks(5);
        departmentDTONew.setPhoneNumber(911);

        DepartmentDTO department = new DepartmentDTO();
        department.setId(departmentID);
        department.setName(departmentDTONew.getName());
        department.setEmail(departmentDTONew.getEmail());
        department.setYearWorks(departmentDTONew.getYearWorks());
        department.setPhoneNumber(departmentDTONew.getPhoneNumber());

        when(departmentService.update(departmentID, departmentDTONew)).thenReturn(department);

        DepartmentDTO response = departmentController.updateDepartment(departmentID, departmentDTONew);

        assertEquals(department, response);

        verify(departmentService, times(1)).update(departmentID, departmentDTONew);
    }

    @Test
    void deleteDepartment() {
        Long departmentID = 1L;

        assertEquals("Success delete department with id " + departmentID, departmentController.deleteDepartment(departmentID));
    }
}
