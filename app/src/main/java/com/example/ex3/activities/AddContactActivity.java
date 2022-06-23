package com.example.ex3.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex3.App;
import com.example.ex3.R;
import com.example.ex3.entities.ContactDetails;
import com.google.android.material.textfield.TextInputEditText;

public class AddContactActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        TextView tvErrUsername = findViewById(R.id.tvErrUsername);
        TextView tvErrNickname = findViewById(R.id.tvErrNickname);
        TextView tvErrServer = findViewById(R.id.tvErrServer);

        TextInputEditText usernameContact = findViewById(R.id.usernameContact);
        TextInputEditText nicknameContact = findViewById(R.id.nicknameContact);
        TextInputEditText serverContact = findViewById(R.id.serverContact);

        Button btnSaveNewContact = findViewById(R.id.btnSaveNewContact);
        btnSaveNewContact.setOnClickListener(view -> {

            String username = usernameContact.getEditableText().toString();
            String nickname = nicknameContact.getEditableText().toString();
            String server = serverContact.getEditableText().toString();

            // client side validations:
            if (username.isEmpty()) {
                tvErrUsername.setText(R.string.errEmptyContactUsername);
            } else if (username.equals(App.USERNAME)) {
                tvErrUsername.setText(R.string.errContactUsernameIsUsername);
            } else {
                tvErrUsername.setText("");
            }

            if (nickname.isEmpty()) {
                tvErrNickname.setText(R.string.errEmptyContactNickname);
            } else {
                tvErrNickname.setText("");
            }

            if (server.isEmpty()) {
                tvErrServer.setText(getString(R.string.emptyServerErr) + " " + getString(R.string.BaseUrl));
            } else {
                tvErrServer.setText("");
            }

            new ContactDetails(username, nickname, server);
        });
    }
}