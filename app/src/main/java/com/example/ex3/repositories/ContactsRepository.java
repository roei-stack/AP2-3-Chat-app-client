package com.example.ex3.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3.App;
import com.example.ex3.api.ContactAPI;
import com.example.ex3.entities.Contact;
import com.example.ex3.entities.ContactDetails;
import com.example.ex3.room.AppDB;
import com.example.ex3.room.ContactDao;

import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {

    private ContactDao dao;
    private ContactListData contactListData;
    private ContactAPI api;

    public ContactsRepository() {
        AppDB db = App.DB;
        dao = db.contactDao();
        contactListData = new ContactListData();
        api = new ContactAPI(contactListData, dao);
    }

    class ContactListData extends MutableLiveData<List<Contact>> {
        public ContactListData() {
            super();
            setValue(new ArrayList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> contactListData.postValue(dao.index())).start();
            // asynchronously retrieve data
            api.get();
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }


    public void add(final ContactDetails contact) {
        api.add(contact);
    }

    /*

    public void delete(final Contact contact) {
        api.delete(contact);
    }*/

    public void reload() {
        api.get();
    }
}
