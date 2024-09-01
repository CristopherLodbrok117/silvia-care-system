package com.silvia_care.notes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("notes")
@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {

    List<Note> findAll();

    List<Note> findByUser_Name(String name);

    List<Note> findByUser_Id(Long id);

    //@Query("SELECT n FROM Note n WHERE n.title LIKE ?1")
    List<Note> findByTitleContains(String title);

    List<Note> findByDetailContains(String detail);

    List<Note> findByActiveTrue();
}
