package com.ivan.third_homework.dto;

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
}
