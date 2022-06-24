package com.example.ex3.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ex3.App;
import com.example.ex3.R;
import com.example.ex3.api.WebServiceAPI;
import com.example.ex3.entities.UserDetails;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private WebServiceAPI webServiceAPI;
    private TextView tvErrRegisterUsername;
    private TextView tvErrRegisterPassword;
    private TextView tvErrRegisterPasswordConfirm;
    private Uri profilePic;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // SETUP go to register button
        findViewById(R.id.btn_go_register).setOnClickListener(this::goToLogin);

        // SETUP image upload
        ImageView user_image = findViewById(R.id.user_image);
        Button btn_upload_image = findViewById(R.id.btn_upload_image);
        ActivityResultLauncher<String> imageLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(), result -> {
                    user_image.setImageURI(result);
                    profilePic = result;
                }
        );
        btn_upload_image.setOnClickListener(v -> imageLauncher.launch("image/*"));

        TextInputEditText login_username = findViewById(R.id.login_username);
        TextInputEditText nickname = findViewById(R.id.nickname);
        TextInputEditText login_password = findViewById(R.id.login_password);
        TextInputEditText password_confirm = findViewById(R.id.password_confirm);

        tvErrRegisterUsername = findViewById(R.id.tvErrRegisterUsername);
        tvErrRegisterPassword = findViewById(R.id.tvErrRegisterPassword);
        tvErrRegisterPasswordConfirm = findViewById(R.id.tvErrRegisterPasswordConfirm);

        webServiceAPI = new Retrofit.Builder()
                .baseUrl(App.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WebServiceAPI.class);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(v -> {
            String username = login_username.getEditableText().toString();
            String nick = nickname.getEditableText().toString();
            String password = login_password.getEditableText().toString();
            String password_conf = password_confirm.getEditableText().toString();

            if (nick.isEmpty()) {
                nick = username;
            }

            if (clientSideValidations(username, password, password_conf)) {
                attemptRegister(v, new UserDetails(username, password, nick), profilePic);
            }
        });
    }

    private boolean clientSideValidations(String username, String password, String password_conf) {
        boolean error = false;
        if (username.isEmpty()) {
            error = true;
            tvErrRegisterUsername.setText(R.string.errEmptyUsername);
        } else {
            tvErrRegisterUsername.setText("");
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
        return !error;
    }

    private void attemptRegister(View v, UserDetails userDetails, Uri profilePic) {
        Call<Void> call = webServiceAPI.registerUser(userDetails);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(App.CONTEXT, "This username already exists"
                            , Toast.LENGTH_LONG).show();

                } else {
                    String pic = "";
                    if (profilePic != null) {
                        pic = profilePic.toString();
                    }
                    // save image
                    SharedPreferences settings = getSharedPreferences("profilePictures", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString(userDetails.getUsername(), pic).apply();
                    // go to login page
                    goToLogin(v);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(App.CONTEXT, "Connection tim out, try again later"
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    private void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
