package com.example.ex3.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex3.R;
import com.example.ex3.adapters.MessageListAdapter;
import com.example.ex3.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private RecyclerView messageRecycler;
    private MessageListAdapter messageAdapter;

    private List<Message> messageList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



        Message m1 = new Message();
        m1.setCreated("42");
        m1.setSent(true);
        m1.setContactId(1);
        m1.setContent("boris is the name");
        messageList.add(m1);

        Message m2 = new Message();
        m2.setCreated("69");
        m2.setSent(false);
        m2.setContactId(2);
        m2.setContent("Yo baby");
        messageList.add(m2);

        messageList.add(new Message(1, "Hello World", true, "time to shine baby", 1));

        messageRecycler = (RecyclerView) findViewById(R.id.recycler_list_id);
        messageAdapter = new MessageListAdapter(this, messageList);
        messageRecycler.setLayoutManager(new LinearLayoutManager(this));
        messageRecycler.setAdapter(messageAdapter);
    }
}