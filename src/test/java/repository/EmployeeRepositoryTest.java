package repository;

import com.ivan.third_homework.entity.Department;
import com.ivan.third_homework.entity.Employees;
import com.ivan.third_homework.entity.Hobbies;
import com.ivan.third_homework.repository.DepartmentRepository;
import com.ivan.third_homework.repository.EmployeeRepository;
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
public class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    HobbiesRepository hobbiesRepository;

    @Test
    void getByIDAndSaveFlushEmployeeTest() {
        Department department = new Department();
        department.setName("IT");
        department.setPhoneNumber(123);
        department.setEmail("supercompany@gmail.com");
        department.setYearWorks(5);

        departmentRepository.saveAndFlush(department);

        Employees employees = new Employees();
        employees.setName("John");
        employees.setSurname("Snow");
        employees.setSalary(500);
        employees.setWorkExp(3);
        employees.setDepartment(department);

        employeeRepository.saveAndFlush(employees);

        Long employeeID = employees.getId();
        Optional<Employees> emp = employeeRepository.findById(employeeID);
        assertTrue(emp.isPresent());
        assertEquals("John", emp.get().getName());
    }

    @Test
    void saveAndFindAllEEmployees() {
        Department department = new Department();
        department.setName("IT");
        department.setPhoneNumber(123);
        department.setEmail("supercompany@gmail.com");
        department.setYearWorks(5);

        departmentRepository.saveAndFlush(department);

        Employees employees1 = new Employees();
        employees1.setName("John");
        employees1.setSurname("Snow");
        employees1.setSalary(500);
        employees1.setWorkExp(3);
        employees1.setDepartment(department);

        Employees employees2 = new Employees();
        employees2.setName("Lina");
        employees2.setSurname("Brown");
        employees2.setSalary(300);
        employees2.setWorkExp(1);
        employees2.setDepartment(department);

        employeeRepository.saveAll(List.of(employees1, employees2));

        List<Employees> employeesList = employeeRepository.findAll();
        assertNotNull(employeesList);
        assertEquals(2, employeesList.size());
        assertEquals("John", employeesList.get(0).getName());
        assertEquals(1, employeesList.get(1).getWorkExp());
    }

    @Test
    void deleteEmployeeTest() {
        Department department = new Department();
        department.setName("IT");
        department.setPhoneNumber(123);
        department.setEmail("supercompany@gmail.com");
        department.setYearWorks(5);

        departmentRepository.saveAndFlush(department);

        Employees employee = new Employees();
        employee.setName("John");
        employee.setSurname("Snow");
        employee.setSalary(500);
        employee.setWorkExp(3);
        employee.setDepartment(department);

        employeeRepository.saveAndFlush(employee);

        assertTrue(employeeRepository.existsById(employee.getId()));

        employeeRepository.deleteById(employee.getId());

        assertFalse(employeeRepository.existsById(employee.getId()));
    }

    @Test
    void updateEmployeeTest() {
        Department department = new Department();
        department.setName("IT");
        department.setPhoneNumber(123);
        department.setEmail("supercompany@gmail.com");
        department.setYearWorks(5);

        departmentRepository.saveAndFlush(department);

        Employees employee = new Employees();
        employee.setName("John");
        employee.setSurname("Snow");
        employee.setSalary(500);
        employee.setWorkExp(3);
        employee.setDepartment(department);

        employeeRepository.saveAndFlush(employee);
        Long empID = employee.getId();
        Employees updatedEmployee = employeeRepository.findById(empID).orElse(null);
        assertNotNull(updatedEmployee);

        updatedEmployee.setName("Tom");
        employeeRepository.saveAndFlush(updatedEmployee);

        Employees newEmployee = employeeRepository.findById(empID).orElse(null);
        assertNotNull(newEmployee);
        assertEquals("Tom", newEmployee.getName());
        assertEquals("Snow", newEmployee.getSurname());
    }

    @Test
    void findByHobbyIDTest() {
        Hobbies hobby = new Hobbies();
        hobby.setName("Videogames");
        hobbiesRepository.saveAndFlush(hobby);

        Department department = new Department();
        department.setName("IT");
        department.setPhoneNumber(123);
        department.setEmail("supercompany@gmail.com");
        department.setYearWorks(5);

        departmentRepository.saveAndFlush(department);

        Employees employee = new Employees();
        employee.setName("John");
        employee.setSurname("Snow");
        employee.setSalary(500);
        employee.setWorkExp(3);
        employee.setHobbies(List.of(hobby));
        employee.setDepartment(department);

        employeeRepository.saveAndFlush(employee);

        List<Employees> employees = employeeRepository.findByHobbyId(hobby.getId());
        assertEquals(1, employees.size());
    }
}
