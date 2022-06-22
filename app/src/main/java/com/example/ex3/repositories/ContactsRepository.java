package com.example.ex3.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3.api.ContactAPI;
import com.example.ex3.entities.Contact;
import com.example.ex3.room.ContactDao;

import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {

    private ContactDao dao;
    private ContactListData contactListData;
    private ContactAPI api;

    public ContactsRepository() {
        //LocalDatabase db = LocalDatabase.getInstance();
        // dao = db.contactsDao();
        contactListData = new ContactListData();
        //api = new ContactAPI(contactListData, dao, "bob");
    }

    class ContactListData extends MutableLiveData<List<Contact>> {
        public ContactListData() {
            super();

            ArrayList<Contact> x = new ArrayList<>();

            x.add(new Contact(1, "bbb", "name", "xxxx", "hi how are ya", "01/01/0001", "x"));

            setValue(x);
        }

        @Override
        protected void onActive() {
            super.onActive();
            //new Thread(() -> contactListData.postValue(dao.index())).start();
            ContactAPI contactAPI = new ContactAPI(null, null, "bob");
            contactAPI.get(this);
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

  /*  public void add(final Contact contact) {
        api.add(contact);
    }

    public void delete(final Contact contact) {
        api.delete(contact);
    }

    public void reload() {
        api.get(this);
    }*/
}
