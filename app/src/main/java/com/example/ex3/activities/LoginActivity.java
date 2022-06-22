package com.example.ex3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex3.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup login process
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(view -> {
            // ...validations...
            Intent intent = new Intent(this, ChatsSelector.class);
            startActivity(intent);
        });

        // setup moving to register page
        Button btn_go_register = findViewById(R.id.btn_go_register);
        btn_go_register.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });


    }
}