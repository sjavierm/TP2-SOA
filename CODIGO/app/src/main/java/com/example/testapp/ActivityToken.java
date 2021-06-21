package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ActivityToken extends AppCompatActivity {

    private String token;
    private String token_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);
    }

    public void cambiarActivityVacunate(View view) {

        Bundle parametros = this.getIntent().getExtras();
        String datos = parametros.getString("token_refresh");
        token_refresh = datos;
        datos = parametros.getString("token");
        token = datos;

        final EditText et1 = (EditText)view.findViewById(R.id.editText);
        String entrada = et1.getText().toString();

        if( entrada == token){

            Intent intent = new Intent(getApplicationContext(), ActivityVacunate.class);

            intent.putExtra("token_refresh", token_refresh);
            startActivity(intent);
        }
        else{
            Log.d("CallBack", "El token ingresado no es valido" );
        }
    }
}
