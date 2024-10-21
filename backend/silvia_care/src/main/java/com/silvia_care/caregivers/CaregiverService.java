package com.silvia_care.caregivers;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CaregiverService {

    private CaregiverRepository repo;

    public CaregiverService(CaregiverRepository repo) {
        this.repo = repo;
    }
    /*
    * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
String result = encoder.encode("myPassword");
assertTrue(encoder.matches("myPassword", result));*/
    public List<Caregiver> findAll(){
        return repo.findAll();
    }

    public Caregiver findByUsername(String username) {
        Optional<Caregiver> optionalCaregiver = repo.findByUsername(username);

        return optionalCaregiver.orElseThrow(() -> new CaregiverNotFoundException(username));

    }

    public Caregiver findById(Long id){
        Optional<Caregiver> optionalCaregiver = repo.findById(id);

        return optionalCaregiver.orElseThrow(() -> new CaregiverNotFoundException("ID: " + id));
    }

    public boolean login(Caregiver caregiver){

        /* Database config pending */

        return true;

    }
}
