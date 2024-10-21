package com.silvia_care.caregivers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("caregiverRepository")
@Repository
public interface CaregiverRepository extends CrudRepository<Caregiver, Long> {
    Optional<Caregiver> findByUsername(String username);

    Optional<Caregiver> findByUsernameAndPassword(String name, String password);

    List<Caregiver> findAll();

    /*@Modifying
    @Query("update users u set u.connected = :connected where u.id = :id")
    void updateConnection(@Param("id") long id, @Param("connected") boolean connected);*/
}
