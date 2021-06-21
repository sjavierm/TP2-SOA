package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.testapp.io.ApiAdapter;
import com.example.testapp.io.response.RegistrarseResponse;
import com.example.testapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRegistrarse extends AppCompatActivity {

    User user = new User("TEST", "Javier", "Benitez", 38445441, "javierbenitez.38445441@gmail.com", "Lolapaluza", 3900, 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Call<RegistrarseResponse> call = ApiAdapter.getApiService().registrarse("application/json", user);
        call.enqueue(new Callback<RegistrarseResponse>() {


            @Override
            public void onResponse(Call<RegistrarseResponse> call, Response<RegistrarseResponse> response) {

                Log.d("CallBack", " response is " + response);

                String token_refresh = response.body().getToken_refresh();
                String token = response.body().getToken_refresh();

                Intent intent = new Intent(getApplicationContext(), ActivityToken.class);

                enviarEmail(token);

                intent.putExtra("token_refresh", token_refresh);
                startActivity(intent);
            }

            public void enviarEmail(String token) {

                //Instanciamos un Intent del tipo ACTION_SEND
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                //Aqui definimos la tipologia de datos del contenido dle Email en este caso text/html
                emailIntent.setType("text/html");
                // Indicamos con un Array de tipo String las direcciones de correo a las cuales enviar
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{user.getEmail()});
                // Aqui definimos un titulo para el Email
                emailIntent.putExtra(android.content.Intent.EXTRA_TITLE, "Vacunate");
                // Aqui definimos un Asunto para el Email
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Token de Inicio de Sesion");
                // Aqui obtenemos la referencia al texto y lo pasamos al Email Intent
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, token);
                try {
                    //Enviamos el Correo iniciando una nueva Activity con el emailIntent.
                    startActivity(Intent.createChooser(emailIntent, "Enviar Correo..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "No hay ningun cliente de correo instalado.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegistrarseResponse> error, Throwable t) {

                Log.d("CallBack", " Throwable is " + t);
            }

        });
    }
}




















