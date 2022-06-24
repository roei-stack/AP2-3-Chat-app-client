package com.example.ex3.entities;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Objects;

@Entity
public class Message {

    @PrimaryKey
    private int id;

    private String content;

    private boolean sent;

    private String created;

    private String contactId;

    @SuppressLint("SimpleDateFormat")
    public Message(int id, String content, boolean sent, String created, String contactId) {
        this.id = id;
        this.content = content;
        this.sent = sent;
        this.contactId = contactId;
        try {
            this.created = new SimpleDateFormat("dd/MM/yyyy hh:mm")
                    .format(Objects.requireNonNull(
                            new SimpleDateFormat(
                                    "yyyy-MM-dd hh:mm:ss"
                            ).parse(created.substring(0, 10) + ' ' + created.substring(11))));
        } catch (Exception e) {
            // i have no idea why,
            // but sometimes created is in the desired format, so i added this line
            this.created = created;
            e.printStackTrace();
        }
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

    public String getContactId() {
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

    public void setContactId(String contactId) {
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
