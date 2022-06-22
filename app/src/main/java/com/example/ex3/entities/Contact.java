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
