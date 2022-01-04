package com.culturalfund.serviceidentity.repo.model;

import com.sun.istack.NotNull;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserType userType;
    private LocalDate registeredAt;

    public User() {
    }

    public User(String name, String surname, String email, String password, UserType userType, LocalDate registeredAt) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.registeredAt = registeredAt;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDate registeredAt) {
        this.registeredAt = registeredAt;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
