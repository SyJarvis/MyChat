package com.taihua.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private int status;
    private Date created_time;
    private Date updated_time;

    public User(){};

    public User(int id, String username, String password, int status, Date created_time, Date updated_time) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.created_time = created_time;
        this.updated_time = updated_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public Date getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Date updated_time) {
        this.updated_time = updated_time;
    }
}
