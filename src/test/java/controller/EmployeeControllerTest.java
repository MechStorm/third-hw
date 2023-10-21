package controller;

import com.ivan.third_homework.controller.EmployeeController;
import com.ivan.third_homework.dto.EmployeeDTO;
import com.ivan.third_homework.dto.EmployeeDTONew;
import com.ivan.third_homework.entity.Department;
import com.ivan.third_homework.service.EmployeeService;
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
public class EmployeeControllerTest {
    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeService employeeService;

    @Test
    void getAllEmployees() {
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        EmployeeDTO employeeDTO2 = new EmployeeDTO();

        List<EmployeeDTO> employees = new ArrayList<>(List.of(employeeDTO1, employeeDTO2));
        when(employeeService.getAll()).thenReturn(employees);

        List<EmployeeDTO> response = employeeController.getAllEmployees();

        assertEquals(employees, response);

        verify(employeeService, times(1)).getAll();
    }

    @Test
    void getEmployeeByID() {
        Department department = new Department(1L, "IT", 111, "super-company@mail.com", 10);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        Long employeeID = 1L;
        employeeDTO.setId(employeeID);
        employeeDTO.setName("John");
        employeeDTO.setSurname("Snow");
        employeeDTO.setSalary(100);
        employeeDTO.setWorkExp(1);
        employeeDTO.setDepartmentId(department.getId());

        when(employeeService.getByID(employeeID)).thenReturn(employeeDTO);

        EmployeeDTO response = employeeController.getEmployeeByID(employeeID);

        assertEquals(employeeDTO, response);

        verify(employeeService, times(1)).getByID(employeeID);
    }

    @Test
    void createNewEmployee() {
        Department department = new Department(1L, "IT", 111, "super-company@mail.com", 10);
        EmployeeDTONew employeeDTONew = new EmployeeDTONew();
        employeeDTONew.setName("John");
        employeeDTONew.setSurname("Snow");
        employeeDTONew.setSalary(100);
        employeeDTONew.setWorkExp(1);
        employeeDTONew.setDepartmentId(department.getId());

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setName(employeeDTONew.getName());
        employeeDTO.setSurname(employeeDTONew.getSurname());
        employeeDTO.setSalary(employeeDTONew.getSalary());
        employeeDTO.setWorkExp(employeeDTONew.getWorkExp());
        employeeDTO.setDepartmentId(employeeDTONew.getDepartmentId());

        when(employeeService.create(employeeDTONew)).thenReturn(employeeDTO);

        EmployeeDTO response = employeeController.createEmployee(employeeDTONew);

        assertEquals(employeeDTO, response);

        verify(employeeService, times(1)).create(employeeDTONew);
    }

    @Test
    void updateEmployee() {
        Long employeeID = 1L;
        Department department = new Department(1L, "IT", 111, "super-company@mail.com", 10);
        EmployeeDTONew employeeDTONew = new EmployeeDTONew();
        employeeDTONew.setName("John");
        employeeDTONew.setSurname("Snow");
        employeeDTONew.setSalary(100);
        employeeDTONew.setWorkExp(1);
        employeeDTONew.setDepartmentId(department.getId());

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeID);
        employeeDTO.setName(employeeDTONew.getName());
        employeeDTO.setSurname(employeeDTONew.getSurname());
        employeeDTO.setSalary(employeeDTONew.getSalary());
        employeeDTO.setWorkExp(employeeDTONew.getWorkExp());
        employeeDTO.setDepartmentId(employeeDTONew.getDepartmentId());

        when(employeeService.update(employeeID, employeeDTONew)).thenReturn(employeeDTO);

        EmployeeDTO response = employeeController.updateEmployee(employeeID, employeeDTONew);

        assertEquals(employeeDTO, response);

        verify(employeeService, times(1)).update(employeeID, employeeDTONew);
    }

    @Test
    void deleteEmployee() {
        Long employeeID = 1L;

        assertEquals("Success delete employee with id " + employeeID, employeeController.deleteEmployee(employeeID));
    }

    @Test
    void setHobbyToEmployeeTest() {
        Long employeeID = 1L;
        Long hobbyID = 1L;

        assertEquals("Employee with id " + employeeID + " has hobby with id " + hobbyID, employeeController.setHobbyToEmployee(employeeID, hobbyID));
    }

    @Test
    void getBooksFromHobbyIDTest() {
        Long hobbyID = 1L;

        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        EmployeeDTO employeeDTO2 = new EmployeeDTO();

        List<EmployeeDTO> employees = new ArrayList<>(List.of(employeeDTO1, employeeDTO2));

        when(employeeService.findByHobbyId(hobbyID)).thenReturn(employees);

        List<EmployeeDTO> response = employeeController.getBooksFromHobbyID(hobbyID);

        assertEquals(employees, response);

        verify(employeeService, times(1)).findByHobbyId(hobbyID);
    }
}
