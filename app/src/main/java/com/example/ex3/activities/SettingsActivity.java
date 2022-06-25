package com.example.ex3.activities;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex3.App;
import com.example.ex3.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences settings = getSharedPreferences("info", 0);
        EditText etAddress = findViewById(R.id.etAddress);
        etAddress.setText(App.API_URL);

        SharedPreferences pictures = getSharedPreferences("profilePictures", 0);
        String picture = pictures.getString(App.USERNAME, "");
        if (!picture.isEmpty()) {
            ImageView contactImg = findViewById(R.id.contactImg);
            try {
                contactImg.setImageURI(Uri.parse(picture));
            } catch (Exception ignored) {}
        }

        findViewById(R.id.btnSaveAddress).setOnClickListener(view -> {
            String newAddress = etAddress.getEditableText().toString();
            if (newAddress.isEmpty() || newAddress.equals(App.API_URL)) {
                Toast.makeText(this, "Not modified", Toast.LENGTH_LONG).show();
            }
            // Remove Last Character if it is /
            newAddress = newAddress.replaceAll("/$", "");

            SharedPreferences.Editor editor = settings.edit();
            editor.putString("apiAddress", newAddress);
            editor.apply();
            App.API_URL = newAddress;
        });

    }
}