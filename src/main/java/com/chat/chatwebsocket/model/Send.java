package com.chat.chatwebsocket.model;

import java.io.Serializable;

/**
 * Created by ahmed on 30/05/2018.
 */
public class Send implements Serializable {
    private String fromuser;
    private String touser;

    public Send() {
    }

    public Send(String fromuser, String touser) {
        this.fromuser = fromuser;
        this.touser = touser;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }
}
