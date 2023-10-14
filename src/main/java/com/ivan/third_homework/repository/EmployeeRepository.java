package com.ivan.third_homework.repository;

import com.ivan.third_homework.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    @Query("select e from Employees e join e.hobbies h where h.id = :hobbyId")
    List<Employees> findByHobbyId(@Param("hobbyId") Long hobbyId);
}
