package com.silvia_care.notes;

import com.silvia_care.caregivers.Caregiver;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
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

    public Note() {}

    public Note(String title, String detail, LocalDate date, Caregiver caregiver){
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.active = true;
        this.caregiver = caregiver;
    }

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDetail(){
        return detail;
    }

    public LocalDate getDate(){
        return date;
    }

    public Caregiver getCaregiver(){
        return caregiver;
    }

    public boolean isActive(){
        return active;
    }

}
