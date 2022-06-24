package com.example.ex3.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ex3.entities.Message;
import com.example.ex3.repositories.MessagesRepository;

import java.util.List;

public class MessageViewModel extends ViewModel {

    private final MessagesRepository repository;

    private final LiveData<List<Message>> messages;

    public MessageViewModel() {
        repository = new MessagesRepository();
        messages = repository.getAll();
    }

    public LiveData<List<Message>> get() {
        return messages;
    }

    public void add(Message m) {
        repository.add(m);
    }

    /*public void delete(Message c) {
        repository.delete(c)
    }*/

    public void reload() {
        repository.reload();
    }
}
