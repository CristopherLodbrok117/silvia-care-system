package com.silvia_care.users;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }
    /*
    * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
String result = encoder.encode("myPassword");
assertTrue(encoder.matches("myPassword", result));*/
    public List<User> findAll(){
        return repo.findAll();
    }

    public User findByName(String name) {
        Optional<User> optionalUser = Optional.ofNullable(repo.findByName(name));

        return optionalUser.orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

    }

    public User findById(Long id){
        Optional<User> optionalUser = repo.findById(id);

        return optionalUser.orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
    }

    public boolean login(User user){

        /* Database config pending */

        return true;

    }
}
