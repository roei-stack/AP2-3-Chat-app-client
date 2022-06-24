package com.example.ex3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ex3.R;
import com.google.android.material.textfield.TextInputEditText;

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

        TextInputEditText login_username = findViewById(R.id.login_username);
        TextInputEditText nickname = findViewById(R.id.nickname);
        TextInputEditText login_password = findViewById(R.id.login_password);
        TextInputEditText password_confirm = findViewById(R.id.password_confirm);

        TextView tvErrRegisterUsername = findViewById(R.id.tvErrRegisterUsername);
        TextView tvErrRegisterNickname = findViewById(R.id.tvErrRegisterNickname);
        TextView tvErrRegisterPassword = findViewById(R.id.tvErrRegisterPassword);
        TextView tvErrRegisterPasswordConfirm = findViewById(R.id.tvErrRegisterPasswordConfirm);

        // SETUP go to register button
        findViewById(R.id.btn_go_register).setOnClickListener(this::goToLogin);

        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(v -> {
            // client side validations
            boolean error = false;

            String username = login_username.getEditableText().toString();
            String nick = nickname.getEditableText().toString();
            String password = login_password.getEditableText().toString();
            String password_conf = password_confirm.getEditableText().toString();

            if (username.isEmpty()) {
                error = true;
                tvErrRegisterUsername.setText(R.string.errEmptyUsername);
            } else {
                tvErrRegisterUsername.setText("");
            }

            if (nick.isEmpty()) {
                nick = username;
            }

            if (password.isEmpty() || !password.matches(".*[a-zA-Z]+.*")
                    || !password.matches(".*\\d.*")) {
                error = true;
                tvErrRegisterPassword.setText(R.string.errInvalidPassword);
            } else {
                tvErrRegisterPassword.setText("");
            }

            if (!password_conf.equals(password)) {
                error = true;
                tvErrRegisterPasswordConfirm.setText(R.string.errPassConfirm);
            } else {
                tvErrRegisterPasswordConfirm.setText("");
            }

            if (!error) {
                // todo attempt register
            }
            //sorry, wrong username/password
        });
    }

    private void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}