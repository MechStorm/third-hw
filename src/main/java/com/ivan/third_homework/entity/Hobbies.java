package com.ivan.third_homework.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hobbies")
public class Hobbies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    public Hobbies() {
    }

    public Hobbies(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hobbies(String name) {
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
        return "Hobbies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}