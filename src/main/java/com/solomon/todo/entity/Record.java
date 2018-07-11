package com.solomon.todo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="UserMassage")
public class Record {

    public static final int TEXT_LENGTH = 150;


    @Column(updatable = false,nullable = false)
    @ManyToMany
    private UUID id;
    @Column(unique = true, updatable = false, length = 30, nullable = false)
    private String createBy;
    @Column(updatable = false,nullable = false)
    private LocalDateTime createDate;
    @Column(length = TEXT_LENGTH, updatable = false,nullable = false)
    private String text;


}
