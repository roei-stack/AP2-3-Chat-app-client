package com.example.ex3.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ContactDetails implements Serializable {
    private String id;

    private String name;

    private String server;

    public ContactDetails(String id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @NonNull
    @Override
    public String toString() {
        return "ContactDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", server='" + server + '\'' +
                '}';
    }
}
