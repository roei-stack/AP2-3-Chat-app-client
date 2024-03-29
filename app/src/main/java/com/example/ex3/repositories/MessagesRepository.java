package com.example.ex3.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3.App;
import com.example.ex3.api.MessageAPI;
import com.example.ex3.entities.Message;
import com.example.ex3.room.AppDB;
import com.example.ex3.room.MessageDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessagesRepository {
    private final MessageDao dao;
    private final MessageListData messageListData;
    private final MessageAPI api;

    public MessagesRepository() {
        AppDB db = App.DB;
        dao = db.messageDao();
        messageListData = new MessageListData();
        api = new MessageAPI(messageListData, dao);
    }

    class MessageListData extends MutableLiveData<List<Message>> {

        public MessageListData() {
            super();
            setValue(new ArrayList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() -> messageListData.postValue(dao.index())).start();
            // asynchronously retrieve data
            assert App.ACTIVE_CONTACT != null;
            api.get(Objects.requireNonNull(App.ACTIVE_CONTACT.getValue()));
        }
    }

    public LiveData<List<Message>> getAll() {
        return messageListData;
    }

    public void add(final Message m) {
        api.add(m);
    }

    /*public void delete(final Message m) {
        api.delete(m)
    }*/

    public void reload() {
        assert App.ACTIVE_CONTACT != null;
        api.get(Objects.requireNonNull(App.ACTIVE_CONTACT.getValue()));
    }
}
