package com.example.ex3.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String content;

    private boolean sent;

    private String created;

    private int contactId;

    public Message() {}

    public Message(int id, String content, boolean sent, String created, int contactId) {
        this.id = id;
        this.content = content;
        this.sent = sent;
        this.created = created;
        this.contactId = contactId;
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

    public int getContactId() {
        return contactId;
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

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @NonNull
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sent=" + sent +
                ", created='" + created + '\'' +
                ", contactId=" + contactId +
                '}';
    }
}
