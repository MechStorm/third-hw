package com.ivan.third_homework.dto;

public class HobbyDTONew {
    private String name;

    public HobbyDTONew() {
    }

    public HobbyDTONew(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HobbyDTONew{" +
                "name='" + name + '\'' +
                '}';
    }
}
