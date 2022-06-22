package com.example.ex3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ex3.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // SETUP image upload
        ImageView user_image = findViewById(R.id.user_image);
        Button btn_upload_image = findViewById(R.id.btn_upload_image);
        ActivityResultLauncher<String> imageLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(), user_image::setImageURI
        );
        btn_upload_image.setOnClickListener(v -> imageLauncher.launch("image/*"));

        // SETUP go to register button
        Button btn_go_register = findViewById(R.id.btn_go_register);
        btn_go_register.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });


    }
}