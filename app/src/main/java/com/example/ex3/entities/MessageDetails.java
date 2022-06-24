package com.example.ex3.entities;

import androidx.annotation.NonNull;

public class MessageDetails {

    private int id;

    private String content;

    private boolean sent;

    private String created;

    public MessageDetails(int id, String content, boolean sent, String created) {
        this.id = id;
        this.content = content;
        this.sent = sent;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isSent() {
        return sent;
    }

    public String getCreated() {
        return created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @NonNull
    @Override
    public String toString() {
        return "MessageDetails{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sent=" + sent +
                ", created='" + created + '\'' +
                '}';
    }
}
