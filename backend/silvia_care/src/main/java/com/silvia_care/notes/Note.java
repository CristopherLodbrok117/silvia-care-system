package com.silvia_care.notes;

import com.silvia_care.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public Note() {}

    public Note(String title, String detail, LocalDate date, User user){
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.active = true;
        this.user = user;
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

    public User getUser(){
        return user;
    }

    public boolean isActive(){
        return active;
    }

}
