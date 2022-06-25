package com.example.ex3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ex3.App;
import com.example.ex3.R;
import com.example.ex3.api.WebServiceAPI;
import com.example.ex3.entities.UserDetails;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private TextView tvErrLoginUsername;
    private TextView tvErrLoginPassword;
    WebServiceAPI webServiceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup moving to register page
        Button btn_go_register = findViewById(R.id.btn_go_register);
        btn_go_register.setOnClickListener(this::goToRegister);

        TextInputEditText login_username = findViewById(R.id.login_username);
        TextInputEditText login_password = findViewById(R.id.login_password);

        tvErrLoginUsername = findViewById(R.id.tvErrLoginUsername);
        tvErrLoginPassword = findViewById(R.id.tvErrLoginPassword);

        // setup login process
        webServiceAPI = new Retrofit.Builder()
                .baseUrl(App.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WebServiceAPI.class);
        findViewById(R.id.btn_login).setOnClickListener(view -> {
            // client side validations
            String username = login_username.getEditableText().toString();
            String password = login_password.getEditableText().toString();

           if (clientSideValidations(username, password)) {
               attemptLogin(new UserDetails(username, password, null));
           }
        });
    }

    private void attemptLogin(UserDetails userDetails) {
        Call<Void> call = webServiceAPI.loginUser(userDetails);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(App.CONTEXT, "Sorry, wrong username and/or password"
                            , Toast.LENGTH_LONG).show();
                    return;
                }
                App.USERNAME = userDetails.getUsername();
                App.prefs.edit().putString("connected", userDetails.getUsername()).apply();

                // send token to the server
                final boolean[] isSentToServer = {false};
                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(LoginActivity.this, new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String newToken = instanceIdResult.getToken();
                        if (!isSentToServer[0]) {
                            // ***** send newToken to the sever *****
                        }
                        isSentToServer[0] = true;
                    }
                });
                goToChatSelector();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(App.CONTEXT, "Connection timed out"
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    private void goToChatSelector() {
        startActivity(new Intent(this, ChatsSelector.class));
    }

    private boolean clientSideValidations(String username, String password) {
        boolean error = false;
        if (username.isEmpty()) {
            error = true;
            tvErrLoginUsername.setText(R.string.errEmptyUsername);
        } else {
            tvErrLoginUsername.setText("");
        }

        if (password.isEmpty() || !password.matches(".*[a-zA-Z]+.*")
                || !password.matches(".*\\d.*")) {
            error = true;
            tvErrLoginPassword.setText(R.string.errInvalidPassword);
        } else {
            tvErrLoginPassword.setText("");
        }
        return !error;
    }

    private void goToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
