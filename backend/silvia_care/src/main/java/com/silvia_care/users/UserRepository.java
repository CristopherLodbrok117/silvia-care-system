package com.silvia_care.users;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("users")
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);

    Optional<User> findByNameAndPassword(String name, String password);

    List<User> findAll();

    /*@Modifying
    @Query("update users u set u.connected = :connected where u.id = :id")
    void updateConnection(@Param("id") long id, @Param("connected") boolean connected);*/
}
