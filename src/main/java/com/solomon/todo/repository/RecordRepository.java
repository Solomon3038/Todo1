package com.solomon.todo.repository;

import com.solomon.todo.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RecordRepository extends JpaRepository<Record, UUID> {

    @Query("select r from #{#entityName} r where r.tags.name in (:tags)")
    List<Record> findByTags(@Param("tags") List<String> tags);

}
