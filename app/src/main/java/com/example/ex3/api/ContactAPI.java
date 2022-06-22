package com.example.ex3.api;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3.App;
import com.example.ex3.R;
import com.example.ex3.entities.Contact;
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

    public ContactAPI(MutableLiveData<List<Contact>> contactListData, ContactDao dao, String username) {
        this.contactListData = contactListData;
        this.dao = dao;
        this.username = username;
        retrofit = new Retrofit.Builder()
                .baseUrl(App.CONTEXT.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get(MutableLiveData<List<Contact>> contacts) {
        Call<List<Contact>> call = webServiceAPI.getContacts("bob");
        call.enqueue(new Callback<List<Contact>>() {
           @Override
           public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {

               contacts.setValue(response.body());
               // todo add the list to dao and contactListData
               /*new Thread(() -> {
                  // dao.insert(response.body());
                   contactListData.postValue(dao.index());
               }).start();*/
           }

           @Override
            public void onFailure(@NonNull Call<List<Contact>> call, @NonNull Throwable t) {
               Log.e("Fetch failed", t.toString());
           }
        });
    }


}
