package com.example.testapp.io;

import com.example.testapp.io.response.LoginResponse;
import com.example.testapp.io.response.RefreshTokenResponse;
import com.example.testapp.io.response.RegistrarseResponse;
import com.example.testapp.model.Login;
import com.example.testapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    // annotation that used with POST type request

    @POST("register") // specify the sub url for our base url
    Call<RegistrarseResponse> registrarse(
            @Header("content-type:") String header,
            @Body User request
            );

    @POST("login") // specify the sub url for our base url
    Call<LoginResponse> login(
            @Header("content-type:") String header,
            @Body Login request
    );

    @POST("refresh") // specify the sub url for our base url
    Call<RefreshTokenResponse> refresh(
            @Field("content-type:") String content_type,
            @Field("Authorization:Bearer") String token_refresh
    );

}