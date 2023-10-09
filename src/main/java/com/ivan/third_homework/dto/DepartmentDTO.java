package com.ivan.third_homework.dto;

public class DepartmentDTO {
    private Long id;
    private String name;
    private int phoneNumber;
    private String email;
    private int yearWorks;

    public DepartmentDTO(Long id, String name, int phoneNumber, String email, int yearWorks) {

    }

    public DepartmentDTO() {

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
}
