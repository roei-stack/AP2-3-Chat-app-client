package com.example.ex3.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    // id, contact_username, name, server, last, lastDate, image
    @PrimaryKey
    private int id;

    private String contactUsername;

    private String name;

    private String server;

    private String last;

    private String lastDate;

    private String image;

    public Contact(int id,String contactUsername, String name, String server, String last, String lastDate, String image) {
        this.id = id;
        this.contactUsername = contactUsername;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastDate = lastDate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getContactUsername() {
        return contactUsername;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public String getLast() {
        return last;
    }

    public String getLastDate() {
        return lastDate;
    }

    public String getImage() { return image; }

    public void setId(int id) {
        this.id = id;
    }

    public void setContactUsername(String contactUsername) {
        this.contactUsername = contactUsername;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public void setImage(String image) { this.image = image; }

    @NonNull
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactUsername='" + contactUsername + '\'' +
                ", name='" + name + '\'' +
                ", server='" + server + '\'' +
                ", last='" + last + '\'' +
                ", lastDate='" + lastDate + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
