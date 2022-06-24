package com.example.ex3.entities;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
public class Contact implements Serializable {

    // id, contact_username, name, server, last, lastDate, image
    @NonNull
    @PrimaryKey
    private String id; // his username

    private String name; // his nickname

    private String server; // his server

    private String last; // last message in chat

    private String lastdate; // the last message's time

  //  private String image; // his profile picture

    @SuppressLint("SimpleDateFormat")
    public Contact(@NonNull String id, String name, String server, String last, String lastdate) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            Date date = Objects.requireNonNull(
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                    .parse(lastdate.substring(0, 10) + ' '
                            + lastdate.substring(11)));
            this.lastdate = dateFormat.format(date);
        } catch (Exception e) {
            this.lastdate = "";
            e.printStackTrace();
        }
        //    this.image = image;
    }

    @NonNull
    public String getId() {
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

    public String getLastdate() {
        return lastdate;
    }

    //  public String getImage() { return image }

    public void setId(@NonNull String id) {
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

    public void setLastdate(String lastDate) {
        this.lastdate = lastDate;
    }

  /*  public void setImage(String image) { this.image = image; }*/

    @NonNull
    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", server='" + server + '\'' +
                ", last='" + last + '\'' +
                ", lastDate='" + lastdate + '\'' +
                '}';
    }
}
