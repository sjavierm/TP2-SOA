package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.testapp.io.ApiAdapter;
import com.example.testapp.io.response.RefreshTokenResponse;
import com.example.testapp.io.response.RegistrarseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityVacunate extends AppCompatActivity {

    private String token_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle parametros = this.getIntent().getExtras();
        String datos = parametros.getString("token_refresh");
        token_refresh = datos;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunate);
    }

    public void actualizarToken(View view){


        Call<RefreshTokenResponse> call = ApiAdapter.getApiService().refresh("application/json", token_refresh);
        call.enqueue(new Callback<RefreshTokenResponse>() {


            @Override
            public void onResponse(Call<RefreshTokenResponse> call, Response<RefreshTokenResponse> response) {

                Log.d("CallBack", " response is " + response);
            }

            @Override
            public void onFailure(Call<RefreshTokenResponse> error, Throwable t) {

                Log.d("CallBack", " Throwable is " + t);
            }


        });

    }

}
