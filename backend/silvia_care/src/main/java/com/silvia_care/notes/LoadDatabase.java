package com.silvia_care.notes;

import com.silvia_care.caregivers.Caregiver;
import com.silvia_care.caregivers.CaregiverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    private final NoteRepository noteRepo;
    private final CaregiverRepository caregiverRepo;

    public LoadDatabase(@Qualifier("noteRepository") NoteRepository noteRepo
            ,@Qualifier("caregiverRepository") CaregiverRepository caregiverRepo){
        this.noteRepo = noteRepo;
        this.caregiverRepo = caregiverRepo;
    }

    @Bean
    CommandLineRunner initDatabase(){
        return args -> {
            Caregiver alejandra =  caregiverRepo.save(new Caregiver("Alejandra", "1234"));
            Caregiver pepe =  caregiverRepo.save(new Caregiver("Pepe", "1234"));
            Caregiver marco =  caregiverRepo.save(new Caregiver("Marco", "1234"));

            log.info("Preloading " + alejandra.getName());
            log.info("Preloading " + pepe.getName());
            log.info("Preloading " + marco.getName());
            log.info("Preloading " + noteRepo.save(new Note("Recordatorio 1", "Esto es un ejemplo de nota 1", LocalDate.of(2024, 12, 9), alejandra)));
            log.info("Preloading " + noteRepo.save(new Note("Recordatorio 2", "Esto es un ejemplo de nota 2", LocalDate.of(2020, 10, 19), alejandra)));
            log.info("Preloading " + noteRepo.save(new Note("Okidoki", "Esto es un ejemplo de nota 3", LocalDate.of(2016, 11, 29), alejandra)));
            log.info("Preloading " + noteRepo.save(new Note("Okidoki 2", "Esto es un ejemplo de nota 4", LocalDate.of(2016, 1, 20), pepe)));
            log.info("Preloading " + noteRepo.save(new Note("Okidoki 3", "Esto es un ejemplo de nota 5", LocalDate.of(2017, 2, 19), pepe)));
            log.info("Preloading " + noteRepo.save(new Note("Okidoki 4", "Esto es un ejemplo de nota 6", LocalDate.of(2018, 3, 18), marco)));
        };
    }
}
