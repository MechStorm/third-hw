package com.ivan.third_homework.dto;

import java.util.Objects;

public class DepartmentDTO {
    private Long id;
    private String name;
    private int phoneNumber;
    private String email;
    private int yearWorks;

    public DepartmentDTO() {

    }

    public DepartmentDTO(Long id, String name, int phoneNumber, String email, int yearWorks) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.yearWorks = yearWorks;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYearWorks() {
        return yearWorks;
    }

    public void setYearWorks(int yearWorks) {
        this.yearWorks = yearWorks;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", yearWorks=" + yearWorks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDTO that = (DepartmentDTO) o;
        return phoneNumber == that.phoneNumber && yearWorks == that.yearWorks && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email, yearWorks);
    }
}
