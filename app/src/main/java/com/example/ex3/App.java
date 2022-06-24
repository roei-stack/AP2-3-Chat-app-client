package com.example.ex3;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.ex3.entities.Contact;
import com.example.ex3.room.AppDB;

public class App extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context CONTEXT;
    public static AppDB DB;
    public static String USERNAME;
    public static MutableLiveData<Contact> ACTIVE_CONTACT = new MutableLiveData<>();
    public static boolean isInChat = false;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();

        DB = Room.databaseBuilder(getApplicationContext(), AppDB.class, "LocalDB")
                .allowMainThreadQueries()
                .build();
    }
}
