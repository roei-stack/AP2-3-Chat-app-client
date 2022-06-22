package com.example.ex3.api;

import com.example.ex3.entities.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServiceAPI {

    @GET("contacts")
    Call<List<Contact>> getContacts(@Query("username") String username);

    @POST("contacts")
    Call<Void> createContact(String username, @Body Contact contact);

    @DELETE("contacts/{id}")
    Call<Void> deleteContact(String username, @Path("id") int id);
}
