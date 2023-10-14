package com.ivan.third_homework.dto;

public class EmployeeDTONew {
    private String name;
    private String surname;
    private int workExp;
    private int salary;
    private Long departmentId;

    public EmployeeDTONew() {
    }

    public EmployeeDTONew(String name, String surname, int workExp, int salary, Long departmentId) {
        this.name = name;
        this.surname = surname;
        this.workExp = workExp;
        this.salary = salary;
        this.departmentId = departmentId;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "EmployeeDTONew{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", workExp=" + workExp +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                '}';
    }
}
