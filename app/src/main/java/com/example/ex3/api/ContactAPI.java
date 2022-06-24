package com.example.ex3.api;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3.App;
import com.example.ex3.R;
import com.example.ex3.entities.Contact;
import com.example.ex3.entities.ContactDetails;
import com.example.ex3.room.ContactDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {
    private MutableLiveData<List<Contact>> contactListData;
    private ContactDao dao;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    String username;

    public ContactAPI(MutableLiveData<List<Contact>> contactListData, ContactDao dao) {
        this.contactListData = contactListData;
        this.dao = dao;
        this.username = App.USERNAME;
        retrofit = new Retrofit.Builder()
                .baseUrl(App.CONTEXT.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<Contact>> call = webServiceAPI.getContacts(username);
        call.enqueue(new Callback<List<Contact>>() {
           @Override
           public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {
               /*
               if (!response.isSuccessful()) {
                   Toast.makeText(App.CONTEXT, "The server could not find this user"
                                   , Toast.LENGTH_LONG).show();
                   System.exit(0);
                   return;
               }*/
               //contacts.setValue(response.body());
               new Thread(() -> {
                   dao.clear();
                   dao.insertList(response.body());
                   contactListData.postValue(dao.index());
               }).start();
           }

           @Override
            public void onFailure(@NonNull Call<List<Contact>> call, @NonNull Throwable t) {
               Log.e("Fetch failed", t.toString());
               Toast.makeText(App.CONTEXT, "Server timed out "
                       , Toast.LENGTH_LONG).show();
           }
        });
    }


    public void add(ContactDetails contact) {
        if (dao.get(contact.getId()) != null) {
            Toast.makeText(App.CONTEXT, "This contact already exists", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (contact.getServer().equals(App.CONTEXT.getString(R.string.BaseUrl))) {
            contact.setServer(App.CONTEXT.getString(R.string.BaseLocalUrl));
        }
        Call<Void> call = webServiceAPI.createContact(username, contact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(App.CONTEXT,
                                "Operation failed, make sure the server and username are valid",
                                Toast.LENGTH_LONG)
                        .show();
                    return;
                }
                new Thread(() -> {
                    dao.insert(new Contact(contact.getId(), contact.getName(), contact.getServer(),
                            null, null));
                    contactListData.postValue(dao.index());
                }).start();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // a common reason for timeouts is that the user has given an invalid server
                Toast.makeText(App.CONTEXT, "Connection timeout, request failed",
                                Toast.LENGTH_LONG).show();
                Log.e("Fetch failed", t.toString());
            }
        });
    }
}
