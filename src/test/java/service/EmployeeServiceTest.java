package service;

import com.ivan.third_homework.dto.EmployeeDTO;
import com.ivan.third_homework.dto.EmployeeDTONew;
import com.ivan.third_homework.entity.Department;
import com.ivan.third_homework.entity.Employees;
import com.ivan.third_homework.entity.Hobbies;
import com.ivan.third_homework.mapper.EmployeeMapper;
import com.ivan.third_homework.repository.DepartmentRepository;
import com.ivan.third_homework.repository.EmployeeRepository;
import com.ivan.third_homework.repository.HobbiesRepository;
import com.ivan.third_homework.service.EmployeeServiceImpl;
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
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeMapper employeeMapper;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    HobbiesRepository hobbiesRepository;

    @Test
    void getAllEmployeesTest() {
        List<Employees> employees = new ArrayList<>();
        Department department = new Department(1L, "IT", 123, "supercompany@gmail.com", 10);
        Employees employee1 = new Employees(1L, "John", "Shmidt", 5, 500, department);
        Employees employee2 = new Employees(2L, "Luke", "Skywalker", 15, 12500, department);
        employees.addAll(List.of(employee1, employee2));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeDTO> expectedEmployeesDTO = new ArrayList<>();
        expectedEmployeesDTO.add(new EmployeeDTO(1L, "John", "Shmidt", 5, 500, department.getId()));
        expectedEmployeesDTO.add(new EmployeeDTO(2L, "Luke", "Skywalker", 15, 12500, department.getId()));

        when(employeeMapper.toDto(any())).thenAnswer(answer -> {
            Employees employee = answer.getArgument(0);
            return new EmployeeDTO(employee.getId(), employee.getName(), employee.getSurname(), employee.getWorkExp(), employee.getSalary(), employee.getDepartment().getId());
        });

        List<EmployeeDTO> actualEmployeesDTO = employeeService.getAll();
        assertIterableEquals(expectedEmployeesDTO, actualEmployeesDTO);
    }

    @Test
    void getEmployeeByIDTest() {
        Long employeeID = 1L;
        Department department = new Department(1L, "IT", 123, "supercompany@gmail.com", 10);
        Employees employee = new Employees(employeeID,"John", "Shmidt", 5, 500, department);

        when(employeeRepository.findById(employeeID)).thenReturn(Optional.of(employee));

        EmployeeDTO expectedEmployeeDTO = new EmployeeDTO(employeeID,"John", "Shmidt", 5, 500, employee.getDepartment().getId());

        when(employeeMapper.toDto(any())).thenAnswer(answer -> {
            Employees employeeAns = answer.getArgument(0);
            return new EmployeeDTO(employeeAns.getId(), employeeAns.getName(), employeeAns.getSurname(), employeeAns.getWorkExp(), employeeAns.getSalary(), employeeAns.getDepartment().getId());
        });

        EmployeeDTO actualEmployeeDTO = employeeService.getByID(employeeID);
        assertEquals(expectedEmployeeDTO, actualEmployeeDTO);
    }

    @Test
    void createEmployeeTest() {
        Department department = new Department(1L, "IT", 123, "supercompany@gmail.com", 10);
        EmployeeDTONew employeeDTONew = new EmployeeDTONew("John", "Shmidt", 5, 500, department.getId());

        Employees employee = new Employees(1L, "John", "Shmidt", 5, 500, department);

        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        when(employeeMapper.toEntity(employeeDTONew)).thenReturn(employee);
        when(employeeRepository.saveAndFlush(employee)).thenReturn(employee);
        when(employeeMapper.toDto(any(Employees.class))).thenReturn(new EmployeeDTO(1L, "John", "Shmidt", 5, 500, department.getId()));

        EmployeeDTO employeeDTO = employeeService.create(employeeDTONew);

        assertNotNull(employeeDTO);
        assertEquals(1L, employeeDTO.getId());
        assertEquals("John", employeeDTO.getName());
        assertEquals("Shmidt", employeeDTO.getSurname());
        assertEquals(5, employeeDTO.getWorkExp());
        assertEquals(500, employeeDTO.getSalary());
        assertEquals(1L, employeeDTO.getDepartmentId());

        verify(departmentRepository, times(1)).findById(department.getId());
        verify(employeeMapper, times(1)).toEntity(employeeDTONew);
        verify(employeeRepository, times(1)).saveAndFlush(employee);
        verify(employeeMapper, times(1)).toDto(employee);
    }

    @Test
    void updateEmployeeTest() {
        Long employeeID = 1L;
        Department department = new Department(1L, "IT", 123, "supercompany@gmail.com", 10);
        EmployeeDTONew employeeDTONew = new EmployeeDTONew("John", "Shmidt", 5, 500, department.getId());
        Employees employee = new Employees(employeeID, "John", "Shmidt", 5, 500, department);
        Employees updatedEmployees = new Employees(employeeID, "Jack", "Smith", 5, 700, department);

        when(employeeRepository.findById(employeeID)).thenReturn(Optional.of(employee));
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        when(employeeMapper.updateToEntity(employeeDTONew, employee)).thenReturn(updatedEmployees);
        when(employeeRepository.saveAndFlush(any(Employees.class))).thenReturn(updatedEmployees);
        when(employeeMapper.toDto(any(Employees.class))).thenReturn(new EmployeeDTO(employeeID, "Jack", "Smith", 5, 700, department.getId()));

        EmployeeDTO employeeDTO = employeeService.update(employeeID, employeeDTONew);

        assertNotNull(employeeDTO);
        assertEquals(1L, employeeDTO.getId());
        assertEquals("Jack", employeeDTO.getName());
        assertEquals("Smith", employeeDTO.getSurname());
        assertEquals(5, employeeDTO.getWorkExp());
        assertEquals(700, employeeDTO.getSalary());
        assertEquals(1L, employeeDTO.getDepartmentId());

        verify(departmentRepository, times(1)).findById(department.getId());
        verify(employeeRepository, times(1)).findById(employeeID);
        verify(employeeMapper, times(1)).updateToEntity(employeeDTONew, employee);
        verify(employeeRepository, times(1)).saveAndFlush(updatedEmployees);
        verify(employeeMapper, times(1)).toDto(updatedEmployees);
    }

    @Test
    void deleteEmployeeTest() {
        Long employeeID = 1L;

        employeeService.delete(employeeID);

        verify(employeeRepository, times(1)).deleteById(employeeID);
        verify(employeeRepository).deleteById(employeeID);
    }

    @Test
    void setHobbyToEmployeeTest() {
        Long employeeID = 1L;
        Long hobbyID = 5L;

        Department department = new Department(1L, "IT", 123, "supercompany@gmail.com", 10);
        Employees employee = new Employees(employeeID, "John", "Shmidt", 5, 500, department);
        Hobbies hobby = new Hobbies(hobbyID, "Art");

        when(employeeRepository.findById(employeeID)).thenReturn(Optional.of(employee));
        when(hobbiesRepository.findById(hobbyID)).thenReturn(Optional.of(hobby));

        employeeService.setHobbyToEmployee(employeeID, hobbyID);

        assertTrue(employee.getHobbies().contains(hobby));

        verify(employeeRepository, times(1)).findById(employeeID);
        verify(hobbiesRepository, times(1)).findById(hobbyID);
    }

    @Test
    void findByHobbyIDTest() {
        Long hobbyID = 1L;
        List<Employees> employees = new ArrayList<>();
        Department department = new Department(1L, "IT", 123, "supercompany@gmail.com", 10);
        Employees employee1 = new Employees(1L, "John", "Shmidt", 5, 500, department);
        Employees employee2 = new Employees(2L, "Luke", "Skywalker", 15, 12500, department);
        employees.addAll(List.of(employee1, employee2));

        when(employeeRepository.findByHobbyId(hobbyID)).thenReturn(employees);
        when(employeeMapper.toDto(any(Employees.class))).thenAnswer(answer -> {
            Employees emp = answer.getArgument(0);
            return new EmployeeDTO(emp.getId(),emp.getName(), emp.getSurname(), emp.getWorkExp(), emp.getSalary(), emp.getDepartment().getId());
        });

        List<EmployeeDTO> employeesDTO = employeeService.findByHobbyId(hobbyID);

        assertNotNull(employeesDTO);
        assertEquals(2, employeesDTO.size());
        assertEquals("Luke", employeesDTO.get(1).getName());
        assertEquals(500, employeesDTO.get(0).getSalary());
    }
}
