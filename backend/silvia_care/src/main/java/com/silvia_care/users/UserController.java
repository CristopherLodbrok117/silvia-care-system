package com.silvia_care.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<User> findById(@PathVariable Long requestedId){
        User user = service.findById(requestedId);

        return ResponseEntity.ok(user);
    }

    @GetMapping(params = "name")
    public ResponseEntity<User> findByName(@RequestParam("name") String requestedName) {
        User user = service.findByName(requestedName);

        return ResponseEntity.ok(user);
    }



    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User user){
        Boolean connected = service.login(user);

        return ResponseEntity.ok(connected);
    }
}
