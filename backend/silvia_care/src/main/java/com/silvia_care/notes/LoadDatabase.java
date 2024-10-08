package com.silvia_care.notes;

import com.silvia_care.caregivers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
            // Create PERMISSIONS
            PermissionEntity create = PermissionEntity.builder().name("CREATE").build();
            PermissionEntity read = PermissionEntity.builder().name("READ").build();
            PermissionEntity update = PermissionEntity.builder().name("UPDATE").build();
            PermissionEntity delete = PermissionEntity.builder().name("DELETE").build();

            // Create ROLES
            RoleEntity admin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissions(Set.of(create, read, update, delete))
                    .build();

            RoleEntity user = RoleEntity.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissions(Set.of(create, read, delete))
                    .build();

            // Create USERS
            Caregiver alejandra = Caregiver.builder()
                    .username("alejandra")
                    .password("1234")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(admin))
                    .build();

            Caregiver pepe = Caregiver.builder()
                    .username("pepe")
                    .password("1234")
                    .isEnabled(true)
                    .accountNoLocked(true)
                    .accountNoExpired(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(user))
                    .build();

            Caregiver marco = Caregiver.builder()
                    .username("marco")
                    .password("1234")
                    .isEnabled(true)
                    .accountNoLocked(true)
                    .accountNoExpired(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(user))
                    .build();

            Caregiver cris = Caregiver.builder()
                    .username("cristopher")
                    .password("1234")
                    .isEnabled(true)
                    .accountNoLocked(true)
                    .accountNoExpired(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(admin))
                    .build();

            caregiverRepo.saveAll(List.of(alejandra, pepe, marco, cris));

            // NOTE example
            Caregiver ale = caregiverRepo.findByUsername("alejandra");

            Note note = Note.builder()
                    .title("Nota de ejemplo")
                    .detail("Puedes agregar todas las notas que necesites")
                    .date(LocalDate.of(2024, 10, 3))
                    .active(true)
                    .caregiver(ale)
                    .build();

            log.info("Preloading " + alejandra.getUsername());
            log.info("Preloading " + pepe.getUsername());
            log.info("Preloading " + marco.getUsername());
            log.info("Preloading " + marco.getUsername());
            log.info("Preloading " + noteRepo.save(note));

        };
    }
}
