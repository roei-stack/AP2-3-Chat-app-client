package com.example.ex3.api;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3.App;
import com.example.ex3.entities.Contact;
import com.example.ex3.entities.Message;
import com.example.ex3.entities.MessageDetails;
import com.example.ex3.room.MessageDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    private final MutableLiveData<List<Message>> messageListData;
    private final MessageDao dao;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    String username;

    public MessageAPI(MutableLiveData<List<Message>> messageListData, MessageDao dao) {
        this.messageListData = messageListData;
        this.dao = dao;
        this.username = App.USERNAME;
        retrofit = new Retrofit.Builder()
                .baseUrl(App.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get(Contact c) {
        Call<List<MessageDetails>> call = webServiceAPI.getMessages(c.getId(), username);
        call.enqueue(new Callback<List<MessageDetails>>() {
            @Override
            public void onResponse(@NonNull Call<List<MessageDetails>> call, @NonNull Response<List<MessageDetails>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(App.CONTEXT, "The server could not find this user/contact"
                            , Toast.LENGTH_LONG).show();
                    return;
                }
                new Thread(() -> {
                    List<Message> messages = new ArrayList<>();
                    assert response.body() != null;
                    for (MessageDetails m : response.body()) {
                        messages.add(new Message(m.getId(), m.getContent(),
                                m.isSent(), m.getCreated(), c.getId()));
                    }
                    dao.clearAllFromContact(c.getId());
                    dao.insertList(messages);
                    messageListData.postValue(dao.IndexFromContact(c.getId()));
                }).start();
            }

            @Override
            public void onFailure(@NonNull Call<List<MessageDetails>> call, @NonNull Throwable t) {
                Log.e("Fetch failed", t.toString());
                Toast.makeText(App.CONTEXT, "Server timed out"
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void add(Message m) {
        Call<Void> call = webServiceAPI.postMessage(m.getContactId(), username, m.getContent());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.code() == 404) {
                    Toast.makeText(App.CONTEXT, "Error! user/contact not found", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                if (response.code() == 304) {
                    Toast.makeText(App.CONTEXT, "Error! could not transfer message to contact", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                if (!response.isSuccessful()) {
                    Toast.makeText(App.CONTEXT, "Unknown error occurred", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                new Thread(() -> {
                    dao.insert(m);
                    String cid = Objects.requireNonNull(App.ACTIVE_CONTACT.getValue()).getId();
                    messageListData.postValue(dao.IndexFromContact(cid));
                }).start();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(App.CONTEXT, "Connection timeout, request failed",
                        Toast.LENGTH_LONG).show();
                Log.e("Fetch failed", t.toString());
            }
        });
    }
}
