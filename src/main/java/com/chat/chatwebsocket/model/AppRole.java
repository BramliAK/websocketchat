package com.chat.chatwebsocket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ahmed on 27/05/2018.
 */
@Entity
public class AppRole implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String role;

    public AppRole() {
    }

    public AppRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
