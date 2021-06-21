package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.io.ApiAdapter;
import com.example.testapp.io.response.LoginResponse;
import com.example.testapp.model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.constraint);
    }

    /** Called when the user taps the Send button */
    public void cambiarActivityRegistrarse(View view) {
        Intent intent = new Intent(this, ActivityRegistrarse.class);
        startActivity(intent);
    }

    public void cambiarActivityLogin(View view) {

        Login login = new Login("javierbenitez.38445441@gmail.com", "Lolapaluza");

        Call<LoginResponse> call = ApiAdapter.getApiService().login("application/json", login);
        call.enqueue(new Callback<LoginResponse>() {


                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    String token_refresh = response.body().getToken_refresh();
                    String token = response.body().getToken();

                    Log.d("CallBack", " response is " + response);

                    Intent intent = new Intent(getApplicationContext(), ActivityToken.class);
                    intent.putExtra("token_refresh", token_refresh);
                    intent.putExtra("token", token);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<LoginResponse> error, Throwable t) {

                    Log.d("CallBack", " Throwable is " + t);
                }


            });

        }
}
