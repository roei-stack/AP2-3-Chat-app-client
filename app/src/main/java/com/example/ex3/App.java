package com.example.ex3;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.ex3.room.AppDB;

public class App extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context CONTEXT;
    public static AppDB DB;
    public static String USERNAME;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();

        DB = Room.databaseBuilder(getApplicationContext(), AppDB.class, "LocalDB")
                .allowMainThreadQueries()
                .build();
    }
}