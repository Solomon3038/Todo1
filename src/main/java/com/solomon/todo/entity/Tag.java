package com.solomon.todo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "records_id")
    )
    private List<Record> records;

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
}
