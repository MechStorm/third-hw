package service;

import com.ivan.third_homework.dto.DepartmentDTO;
import com.ivan.third_homework.dto.DepartmentDTONew;
import com.ivan.third_homework.entity.Department;
import com.ivan.third_homework.mapper.DepartmentMapper;
import com.ivan.third_homework.repository.DepartmentRepository;
import com.ivan.third_homework.service.DepartmentServiceImpl;
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
public class DepartmentServiceTest {
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    @Mock
    DepartmentRepository departmentRepository;
    @Mock
    DepartmentMapper departmentMapper;

    @Test
    void getAllDepartmentsTest() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(1L, "IT", 123, "johny123@gmail.com", 5));
        departments.add(new Department(2L, "Sales", 456, "johny456@gmail.com", 10));

        when(departmentRepository.findAll()).thenReturn(departments);

        List<DepartmentDTO> departmentDTOs = new ArrayList<>();
        departmentDTOs.add(new DepartmentDTO(1L, "IT", 123, "johny123@gmail.com", 5));
        departmentDTOs.add(new DepartmentDTO(2L, "Sales", 456, "johny456@gmail.com", 10));

        when(departmentMapper.toDto(any())).thenAnswer(answer -> {
            Department department = answer.getArgument(0);
            return new DepartmentDTO(department.getId(), department.getName(), department.getPhoneNumber(), department.getEmail(), department.getYearWorks());
        });

        List<DepartmentDTO> departmentDTOsNew = departmentService.getAll();
        assertIterableEquals(departmentDTOs, departmentDTOsNew);
    }

    @Test
    void getDepartmentByIDTest() {
        Long departmentID = 1L;
        Department department = new Department(departmentID, "IT", 123, "johny123@gmail.com", 5);

        when(departmentRepository.findById(departmentID)).thenReturn(Optional.of(department));

        DepartmentDTO departmentDTO = new DepartmentDTO(departmentID, "IT", 123, "johny123@gmail.com", 5);

        when(departmentMapper.toDto(any())).thenAnswer(answer -> {
            Department dto = answer.getArgument(0);
            return new DepartmentDTO(dto.getId(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getYearWorks());
        });

        DepartmentDTO newDepartmentDTO = departmentService.getByID(departmentID);

        assertEquals(departmentDTO, newDepartmentDTO);
    }

    @Test
    void createDepartmentTest() {
        DepartmentDTONew departmentDTONew = new DepartmentDTONew();
        departmentDTONew.setName("IT");
        departmentDTONew.setPhoneNumber(123);
        departmentDTONew.setEmail("johny123@gmail.com");
        departmentDTONew.setYearWorks(5);

        Department newDepartment = new Department(1L, "IT", 123, "johny123@gmail.com", 5);

        when(departmentMapper.toEntity(departmentDTONew)).thenReturn(newDepartment);
        when(departmentRepository.saveAndFlush(any(Department.class))).thenReturn(newDepartment);
        when(departmentMapper.toDto(any(Department.class))).thenReturn(new DepartmentDTO(1L, "IT", 123, "johny123@gmail.com", 5));

        DepartmentDTO departmentDTO = departmentService.create(departmentDTONew);

        assertNotNull(departmentDTO);
        assertEquals(1L, departmentDTO.getId());
        assertEquals("IT", departmentDTO.getName());
        assertEquals(123, departmentDTO.getPhoneNumber());
        assertEquals("johny123@gmail.com", departmentDTO.getEmail());
        assertEquals(5, departmentDTO.getYearWorks());

        verify(departmentMapper, times(1)).toEntity(departmentDTONew);
        verify(departmentRepository, times(1)).saveAndFlush(newDepartment);
        verify(departmentMapper, times(1)).toDto(newDepartment);
    }

    @Test
    void updateDepartmentTest() {
        Long departmentID = 1L;
        DepartmentDTONew departmentDTONew = new DepartmentDTONew();
        departmentDTONew.setName("Sales");
        departmentDTONew.setPhoneNumber(456);
        departmentDTONew.setEmail("johny456@gmail.com");
        departmentDTONew.setYearWorks(10);

        Department department = new Department(departmentID, "IT", 123, "johny123@gmail.com", 5);
        Department updatedDepartment = new Department(departmentID, "Sales", 456, "johny456@gmail.com", 10);

        when(departmentRepository.findById(departmentID)).thenReturn(Optional.of(department));
        when(departmentMapper.updateDtoToEntity(departmentDTONew, department)).thenReturn(updatedDepartment);
        when(departmentRepository.saveAndFlush(any(Department.class))).thenReturn(updatedDepartment);
        when(departmentMapper.toDto(any(Department.class))).thenReturn(new DepartmentDTO(1L, "Sales", 456, "johny456@gmail.com", 10));

        DepartmentDTO departmentDTOUpdated = departmentService.update(departmentID, departmentDTONew);

        assertNotNull(departmentDTOUpdated);
        assertEquals(1L, departmentDTOUpdated.getId());
        assertEquals("Sales", departmentDTOUpdated.getName());
        assertEquals(456, departmentDTOUpdated.getPhoneNumber());
        assertEquals("johny456@gmail.com", departmentDTOUpdated.getEmail());
        assertEquals(10, departmentDTOUpdated.getYearWorks());

        verify(departmentRepository, times(1)).findById(departmentID);
        verify(departmentMapper, times(1)).updateDtoToEntity(departmentDTONew, department);
        verify(departmentRepository, times(1)).saveAndFlush(updatedDepartment);
        verify(departmentMapper, times(1)).toDto(updatedDepartment);
    }

    @Test
    void deleteDepartmentTest() {
        Long departmentID = 1L;

        departmentService.delete(departmentID);

        verify(departmentRepository, times(1)).deleteById(departmentID);
        verify(departmentRepository).deleteById(departmentID);
    }
}
