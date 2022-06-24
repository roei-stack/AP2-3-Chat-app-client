package com.example.ex3.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ex3.App;
import com.example.ex3.R;
import com.example.ex3.adapters.MessageListAdapter;
import com.example.ex3.entities.Contact;
import com.example.ex3.entities.Message;
import com.example.ex3.viewmodels.MessageViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private RecyclerView messageRecycler;
    private MessageListAdapter messageAdapter;

    private List<Message> messageList = new ArrayList<>();

    private MessageViewModel viewModel;

    @SuppressLint("SimpleDateFormat")
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

        viewModel.get().observe(this, messages -> {
            Collections.sort(messages, (m1, m2) -> {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                try {
                    Date d1 = dateFormat.parse(m1.getCreated());
                    Date d2 = dateFormat.parse(m2.getCreated());
                    assert d1 != null;
                    return d1.compareTo(d2);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            });
            messageAdapter.setMessageList(messages);
        });

        ImageButton send_button = findViewById(R.id.send_button);
        EditText edit_message_box = findViewById(R.id.edit_message_box);

        send_button.setOnClickListener(view -> {
            String content = edit_message_box.getEditableText().toString();
            if (content.isEmpty()) {
                return;
            }
            edit_message_box.setText("");

            viewModel.add(new Message(0, content, true,
                    new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()), contact.getId()));
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        App.isInChat = false;
        App.ACTIVE_CONTACT.setValue(null);
    }
}

/*messageList.add(new Message(1, "weeeeeeee", true, "time to shine baby", "1"));
        messageList.add(new Message(1, "come too far", false, "time to shine baby", "1"));
        messageList.add(new Message(1, "to give up", true, "time to shine baby", "1"));
        messageList.add(new Message(1, "who we aree", false, "time to shine baby", "1"));*/