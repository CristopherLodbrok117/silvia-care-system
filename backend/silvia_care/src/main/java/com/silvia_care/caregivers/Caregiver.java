package com.silvia_care.caregivers;

import com.silvia_care.notes.Note;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "caregivers")
public class Caregiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String password;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @OneToMany(targetEntity = Note.class, fetch = FetchType.LAZY, mappedBy = "caregiver")
    private List<Note> notes;

    public Caregiver() {}

    public Caregiver(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Caregiver(String name, String password, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }


    public void setId(long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }


    public long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin(){
        return isAdmin;
    }

}
