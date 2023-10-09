package com.ivan.third_homework.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "work_exp")
    private int workExp;
    @Column(name = "salary")
    private int salary;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(name = "employees_hobbies",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    private List<Hobbies> hobbies = new ArrayList<>();

    public Employees() {
    }

    public Employees(Long id, String name, String surname, int workExp, int salary, Department department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.workExp = workExp;
        this.salary = salary;
        this.department = department;
    }

    public Employees(String name, String surname, int workExp, int salary, Department department) {
        this.name = name;
        this.surname = surname;
        this.workExp = workExp;
        this.salary = salary;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getWorkExp() {
        return workExp;
    }

    public void setWorkExp(int workExp) {
        this.workExp = workExp;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Hobbies> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobbies> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", workExp=" + workExp +
                ", salary=" + salary +
                ", department=" + department +
                ", hobbies=" + hobbies +
                '}';
    }
}
