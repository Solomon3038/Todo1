package com.solomon.todo.entity;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

    public static final String PREFIX = "#";
    public static final int NAME_LENGHT = 15;

    @Id
    @Column(unique = true, nullable = false, updatable = false, length = NAME_LENGHT)
    @ManyToMany
    private String name;

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return PREFIX + getName();
    }
}
