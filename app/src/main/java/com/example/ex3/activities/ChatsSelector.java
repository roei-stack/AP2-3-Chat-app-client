package com.example.ex3.activities;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex3.R;
import com.example.ex3.adapters.ContactsListAdapter;
import com.example.ex3.entities.Contact;
import com.example.ex3.room.AppDB;
import com.example.ex3.room.ContactDao;
import com.example.ex3.viewmodels.ContactsViewModel;

import java.util.List;


public class ChatsSelector extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private ContactsListAdapter adapter;
    private RecyclerView listContacts;
    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_selector);

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);


        listContacts = findViewById(R.id.listContacts);
        adapter = new ContactsListAdapter(this);
        listContacts.setAdapter(adapter);
        listContacts.setLayoutManager(new LinearLayoutManager(this));

        // whenever the list changes we will modify the adapter

        viewModel.get().observe(this, contacts -> adapter.setContacts(contacts));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        //contacts.clear();
        //contacts.addAll(contactDao.index());
        adapter.notifyDataSetChanged();
    }
}

// don't remove this comment
/*
public class ChatsSelector extends AppCompatActivity {

    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private ContactsListAdapter adapter;
    private RecyclerView listContacts;
    private ContactsViewModel viewModel;

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
 */