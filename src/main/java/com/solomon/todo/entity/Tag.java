package com.solomon.todo.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tags")
public class Tag {

    public static final String PREFIX = "#";
    public static final int NAME_LENGHT = 15;

    @Id
    @Column(unique = true, nullable = false, updatable = false, length = NAME_LENGHT)
    private String name;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "tag_records",
            joinColumns = @JoinColumn(name = "name"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Record> records;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }

    public List<Record> getRecords() {
        return records;
    }

    public Tag setRecords(List<Record> records) {
        this.records = records;
        return this;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(getName(), tag.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
