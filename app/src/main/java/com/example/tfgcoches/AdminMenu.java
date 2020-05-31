package com.example.tfgcoches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
    }

    public void logOut(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void irAnhadir(View v){
        Intent i = new Intent(this, AnhadirVehiculo.class);
        startActivity(i);
    }

    public void irBorrar(View v){
        Intent i = new Intent(this, BorrarVehiculo.class);
        startActivity(i);
    }

    public void irModi(View v){
        Intent i = new Intent(this, ModificarVehiculo.class);
        startActivity(i);
    }
}
