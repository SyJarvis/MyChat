package com.taihua.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class ChatData implements Serializable {
    private User user;
    private Message message;
    private Set<User> online_users;
    public ChatData(){};
    public ChatData(User user, Message message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Set<User> getOnline_users() {
        return online_users;
    }

    public void setOnline_users(Set<User> online_users) {
        this.online_users = online_users;
    }
}
