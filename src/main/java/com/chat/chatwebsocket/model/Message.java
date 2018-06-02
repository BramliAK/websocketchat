package com.chat.chatwebsocket.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ahmed on 27/05/2018.
 */
@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String fromuser;
    private String message;
    private String touser;

    public Message() {
    }

    public Message(String fromuser, String message, String touser) {
        this.fromuser = fromuser;
        this.message = message;
        this.touser = touser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }
}
