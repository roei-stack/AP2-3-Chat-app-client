package com.example.ex3.activities;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.ex3.R;
import com.example.ex3.adapters.ContactsListAdapter;
import com.example.ex3.api.ContactAPI;
import com.example.ex3.entities.Contact;
import com.example.ex3.room.AppDB;
import com.example.ex3.room.ContactDao;

import java.util.List;


public class ChatsSelector extends AppCompatActivity {

    AppDB db;
    ContactDao contactDao;
    List<Contact> contacts;
    ContactsListAdapter adapter;
    RecyclerView listContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_selector);


        ContactAPI contactAPI = new ContactAPI(null, null, "bob");
        contactAPI.get();

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "LocalDB")
                .allowMainThreadQueries()
                .build();
        contactDao = db.contactDao();

      //  db.clearAllTables();
      //  contactDao.insert(new Contact("bob1", "server", "hi how are ya", "22/06/2022", "myURI"));
      //  contactDao.insert(new Contact("bob2", "server", "hi how are ya", "22/06/2022", "myURI"));
      //  contactDao.insert(new Contact("bob3", "server", "hi how are ya", "22/06/2022", "myURI"));
      //  contactDao.insert(new Contact("bob4", "server", "hi how are ya", "22/06/2022", "myURI"));

        contacts = contactDao.index();

        adapter = new ContactsListAdapter(this);
        adapter.setContacts(contacts);

        listContacts = findViewById(R.id.listContacts);
        listContacts.setAdapter(adapter);
        listContacts.setLayoutManager(new LinearLayoutManager(this));

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(contactDao.index());
        adapter.notifyDataSetChanged();
    }
}