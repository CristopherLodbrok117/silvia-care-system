package com.silvia_care.notes;

import com.silvia_care.caregivers.Caregiver;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "detail")
    private String detail;

    @Column(name = "note_date", columnDefinition = "DATE")
    private LocalDate date;

    private boolean active;

    @ManyToOne(targetEntity = Caregiver.class)
    @JoinColumn(name = "caregiver_id")
    private Caregiver caregiver;

}
