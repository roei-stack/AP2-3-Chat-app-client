package com.example.ex3.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex3.R;
import com.example.ex3.adapters.MessageListAdapter;
import com.example.ex3.entities.Contact;
import com.example.ex3.entities.Message;
import com.example.ex3.viewmodels.MessageViewModel;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private RecyclerView messageRecycler;
    private MessageListAdapter messageAdapter;

    private List<Message> messageList = new ArrayList<>();

    private MessageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Contact contact = (Contact) getIntent().getExtras().get("contact");
        viewModel = new ViewModelProvider(this).get(MessageViewModel.class);

        messageRecycler = (RecyclerView) findViewById(R.id.recycler_list_id);
        messageAdapter = new MessageListAdapter(this, messageList);
        messageRecycler.setLayoutManager(new LinearLayoutManager(this));
        messageRecycler.setAdapter(messageAdapter);

        viewModel.get().observe(this, messages -> messageAdapter.setMessageList(messages));



    }

}

/*messageList.add(new Message(1, "weeeeeeee", true, "time to shine baby", "1"));
        messageList.add(new Message(1, "come too far", false, "time to shine baby", "1"));
        messageList.add(new Message(1, "to give up", true, "time to shine baby", "1"));
        messageList.add(new Message(1, "who we aree", false, "time to shine baby", "1"));*/