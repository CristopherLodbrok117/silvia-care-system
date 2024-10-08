package com.silvia_care.caregivers;

import com.silvia_care.notes.Note;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "caregivers")
public class Caregiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    private String password;


    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_no_expired")
    private boolean accountNoExpired;

    @Column(name = "account_no_locked")
    private boolean accountNoLocked;

    @Column(name = "credential_no_expired")
    private boolean credentialNoExpired;

    @OneToMany(targetEntity = Note.class, fetch = FetchType.LAZY, mappedBy = "caregiver")
    private List<Note> notes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "caregiver_roles", joinColumns = @JoinColumn(name = "caregiver_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();



    public Caregiver(String name, String password) {
        this.username = name;
        this.password = password;
        this.isEnabled = true;
        this.accountNoExpired = true;
        this.accountNoLocked = true;
        this.credentialNoExpired = true;
    }




}
