package com.example.ex3.api;

import com.example.ex3.entities.Contact;
import com.example.ex3.entities.ContactDetails;
import com.example.ex3.entities.UserDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServiceAPI {
    @GET("api/contacts")
    Call<List<Contact>> getContacts(@Query("username") String username);

    @POST("api/contacts")
    Call<Void> createContact(@Query("username") String username, @Body ContactDetails contact);

    @DELETE("api/contacts/{id}")
    Call<Void> deleteContact(@Query("username") String username, @Path("id") int id);

    @POST("api/Users/Signup")
    Call<Void> registerUser(@Body UserDetails userDetails);
}
