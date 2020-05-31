package com.example.tfgcoches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AcercaEmpresa extends AppCompatActivity {
    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_empresa);
        datos = getIntent().getExtras();
    }

    public void volverUser(View v){
        Intent i = new Intent(this, UserMenu.class);
        i.putExtra("nombreUsuario", datos.getString("nombreUsuario"));
        startActivity(i);
    }
}
