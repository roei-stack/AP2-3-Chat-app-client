package com.example.ex3.api;

import androidx.lifecycle.MutableLiveData;

import com.example.ex3.App;
import com.example.ex3.R;
import com.example.ex3.entities.Message;
import com.example.ex3.room.MessageDao;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    private MutableLiveData<List<Message>> messageListData;
    private MessageDao dao;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public MessageAPI(MutableLiveData<List<Message>> messageListData, MessageDao dao) {
        this.messageListData = messageListData;
        this.dao = dao;
        retrofit = new Retrofit.Builder()
                .baseUrl(App.CONTEXT.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {

    }

    public void add(Message m) {

    }
}
