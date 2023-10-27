package com.ivan.third_homework.dto;

import java.util.Objects;

public class DepartmentDTONew {
    private String name;
    private int phoneNumber;
    private String email;
    private int yearWorks;

    public DepartmentDTONew() {
    }

    public DepartmentDTONew(String name, int phoneNumber, String email, int yearWorks) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.yearWorks = yearWorks;
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
        return "DepartmentDTONew{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", yearWorks=" + yearWorks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDTONew that = (DepartmentDTONew) o;
        return phoneNumber == that.phoneNumber && yearWorks == that.yearWorks && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, email, yearWorks);
    }
}
