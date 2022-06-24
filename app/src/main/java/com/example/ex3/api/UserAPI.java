package com.example.ex3.api;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ex3.App;
import com.example.ex3.R;
import com.example.ex3.activities.LoginActivity;
import com.example.ex3.entities.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserAPI() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(App.CONTEXT.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void register(UserDetails userDetails) {
        Call<Void> call = webServiceAPI.registerUser(userDetails);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(App.CONTEXT, "This username already exists"
                            , Toast.LENGTH_LONG).show();
                }
                // go to login page
                Intent i = new Intent(App.CONTEXT, LoginActivity.class);

            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(App.CONTEXT, "Connection time out, try again later"
                        , Toast.LENGTH_LONG).show();
            }
        });
    }
}
