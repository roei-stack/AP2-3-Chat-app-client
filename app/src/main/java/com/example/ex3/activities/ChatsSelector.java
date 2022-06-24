package com.example.ex3.activities;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ex3.App;
import com.example.ex3.R;
import com.example.ex3.adapters.ContactsListAdapter;
import com.example.ex3.entities.ContactDetails;
import com.example.ex3.viewmodels.ContactsViewModel;


public class ChatsSelector extends AppCompatActivity {

    private ContactsListAdapter adapter;
    private ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats_selector);

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        App.ACTIVE_CONTACT.observe(this, contact -> {
            if (App.isInChat || contact == null) {
                return;
            }
            App.isInChat = true;
            App.ACTIVE_CONTACT.setValue(contact);
            startActivity(new Intent(this, MessageListActivity.class).putExtra("contact", contact));
        });

        if (getIntent().getExtras() != null) {
            viewModel.add((ContactDetails) getIntent().getExtras().get("contactToAdd"));
        }

        RecyclerView listContactsView = findViewById(R.id.listContacts);
        adapter = new ContactsListAdapter(this);
        listContactsView.setAdapter(adapter);
        listContactsView.setLayoutManager(new LinearLayoutManager(this));

        // when user refreshes, reload the viewModel
        SwipeRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> viewModel.reload());

        // whenever the list changes we will modify the adapter, and stop refreshing
        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
            refreshLayout.setRefreshing(false);
        });

        // add contact button
        findViewById(R.id.floatingActionButtonAdd).setOnClickListener(view ->
            startActivity(new Intent(this, AddContactActivity.class))
        );

        // settings button
        findViewById(R.id.btnSettings).setOnClickListener(view ->
            startActivity(new Intent(this, SettingsActivity.class))
        );
    }
}
