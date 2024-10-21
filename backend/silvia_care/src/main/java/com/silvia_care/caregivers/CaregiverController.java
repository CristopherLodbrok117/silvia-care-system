package com.silvia_care.caregivers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/caregivers")
public class CaregiverController {

    private CaregiverService service;

    public CaregiverController(CaregiverService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Caregiver>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<Caregiver> findById(@PathVariable Long requestedId){
        Caregiver caregiver = service.findById(requestedId);

        return ResponseEntity.ok(caregiver);
    }

    @GetMapping(params = "username")
    public ResponseEntity<Caregiver> findByUsername(@RequestParam("username") String requestedUsername) {
        Caregiver caregiver = service.findByUsername(requestedUsername);

        return ResponseEntity.ok(caregiver);
    }

}
