package com.pm.patientservice.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @NotNull
    private String Name;

    @NotNull
    @Email
    @Column(unique=true)
    private String email;

    @NotNull
    private String address;

    @Column(nullable = false)
    private LocalDate dateofBirth;

    @Column(nullable = false)
    private LocalDate registeredDate;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return Name;
    }

    public void setName(@NotNull String name) {
        Name = name;
    }

    public @NotNull @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }

    public @NotNull String getAddress() {
        return address;
    }

    public void setAddress(@NotNull String address) {
        this.address = address;
    }

    public  LocalDate getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(LocalDate dateofBirth) {

        this.dateofBirth = dateofBirth;
    }

    public  LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate( LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}
