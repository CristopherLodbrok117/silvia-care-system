package com.silvia_care.notes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("noteRepository")
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findAll();

    List<Note> findByCaregiver_Username(String username);

    List<Note> findByCaregiver_Id(Long id);

    //@Query("SELECT n FROM Note n WHERE n.title LIKE ?1")
    List<Note> findByTitleContains(String title);

    List<Note> findByDetailContains(String detail);

    List<Note> findByActiveTrue();



    Optional<Note> findById(Long id);
}
