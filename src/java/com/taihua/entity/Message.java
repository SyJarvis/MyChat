package com.taihua.entity;

import java.io.Serializable;

public class Message implements Serializable {
    private String text;
    private int flag; // 1是私聊，2是群聊

    private String recipient;

    public Message(){};

    public Message(String text, int flag, String recipient) {
        this.text = text;
        this.flag = flag;
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
