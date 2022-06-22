package com.example.ex3.activities;


import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex3.R;
import com.example.ex3.adapters.ContactsListAdapter;
import com.example.ex3.entities.Contact;

import java.util.ArrayList;
import java.util.List;


public class ChatsSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_selector);

        // connect adapter with recycler view
        RecyclerView listContacts = findViewById(R.id.listContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        listContacts.setAdapter(adapter);
        listContacts.setLayoutManager(new LinearLayoutManager(this));




        List<Contact> lst = new ArrayList<>();


        String myURI = Uri.parse("android.resource://com.ex3.here/drawable/image_name").toString();
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        lst.add(new Contact(1, "bob", "server", "hi how are ya", "22/06/2022", myURI));
        adapter.setContacts(lst);

    }
}