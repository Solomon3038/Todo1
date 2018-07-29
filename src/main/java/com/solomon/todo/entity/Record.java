package com.solomon.todo.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.CascadeType.PERSIST;

@Getter
@Setter
@Entity
@Table(name = "user_messages")
@Accessors(chain = true)
public class Record {
    public static final int TEXT_LENGTH = 150;

    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;
//    @Column(unique = true, updatable = false, length = 30, nullable = false)
//    private String createBy;
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDate;
    @Column(length = TEXT_LENGTH, updatable = false, nullable = false)
    private String text;

    @ManyToMany(mappedBy = "records", cascade = PERSIST)
    private Set<Tag> tags;


}
