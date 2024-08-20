package com.silvia_care.notes;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {

    List<Note> findAll();

    List<Note> findAllByUser_Name(String name);

    List<Note> findByTitleLike(String title);

    List<Note> findByDetailLike(String detail);
}
