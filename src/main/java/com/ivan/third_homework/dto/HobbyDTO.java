package com.ivan.third_homework.dto;

import java.util.Objects;

public class HobbyDTO {
    private Long id;
    private String name;

    public HobbyDTO() {
    }

    public HobbyDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "HobbyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyDTO hobbyDTO = (HobbyDTO) o;
        return Objects.equals(id, hobbyDTO.id) && Objects.equals(name, hobbyDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
