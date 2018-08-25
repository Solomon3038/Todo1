package com.solomon.todo.repository;

import com.solomon.todo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,String>{ // String chto za parametr Id

}
