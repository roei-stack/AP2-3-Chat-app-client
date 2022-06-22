package com.example.ex3.entities;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

//@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String server;

    private String last;

    private String lastDate;

    private String image;

    public Contact(int id, String name, String server, String last, String lastDate, String image) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastDate = lastDate;
        this.image = image;
    }

    public int getId() {
        return id;
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
                ", name='" + name + '\'' +
                ", server='" + server + '\'' +
                ", last='" + last + '\'' +
                ", lastDate='" + lastDate + '\'' +
                '}';
    }
}
