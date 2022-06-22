package com.example.ex3.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ex3.entities.Contact;
import com.example.ex3.entities.Message;

@Database(entities = {Contact.class, Message.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract MessageDao messageDao();
}
