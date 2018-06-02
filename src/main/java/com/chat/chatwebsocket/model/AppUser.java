package com.chat.chatwebsocket.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ahmed on 27/05/2018.
 */
@Entity
public class AppUser implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> role=new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String nom, String email, String password, Collection<AppRole> role) {
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.role = role;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public Collection<AppRole> getRole() {
        return role;
    }

    public void setRole(Collection<AppRole> role) {
        this.role = role;
    }
}
