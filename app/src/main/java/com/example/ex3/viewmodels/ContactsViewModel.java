package com.example.ex3.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ex3.entities.Contact;
import com.example.ex3.entities.ContactDetails;
import com.example.ex3.repositories.ContactsRepository;

import java.io.Serializable;
import java.util.List;

public class ContactsViewModel extends ViewModel implements Serializable {

    private final ContactsRepository repository;

    private final LiveData<List<Contact>> contacts;

    public ContactsViewModel() {
        repository = new ContactsRepository();
        contacts = repository.getAll();
    }

    public LiveData<List<Contact>> get() { return contacts; }

    public void add(ContactDetails c) { repository.add(c); }

   // public void delete(Contact c) { repository.delete(c); }

    public void reload() { repository.reload(); }
}
