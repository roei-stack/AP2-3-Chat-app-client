package com.example.ex3;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

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
    public static String API_URL;
    public static SharedPreferences prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();

        DB = Room.databaseBuilder(getApplicationContext(), AppDB.class, "LocalDB")
                .allowMainThreadQueries()
                .build();

        API_URL = getString(R.string.BaseUrl);
        SharedPreferences settings = getSharedPreferences("info", 0);
        if (!settings.getString("apiAddress", "").isEmpty()) {
            API_URL = settings.getString("apiAddress", "");
        }

        prefs = this.getSharedPreferences("com.example.ex3", Context.MODE_PRIVATE);
        if (!prefs.getString("connected", "").isEmpty()) {
            USERNAME = prefs.getString("connected", null);
        }
    }

    public static String getLastLogin() {
        return prefs.getString("connected", null);
    }
}
