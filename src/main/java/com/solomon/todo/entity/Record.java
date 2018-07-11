package com.solomon.todo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "UserMassage")
public class Record {

    public static final int TEXT_LENGTH = 150;


    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(unique = true, updatable = false, length = 30, nullable = false)
    private String createBy;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDate;
    @Column(length = TEXT_LENGTH, updatable = false, nullable = false)
    private String text;
    @ManyToMany(cascade = CascadeType.ALL )
    @JoinColumn(name = "records")
    private List<Tag> tags;


}
