package com.example.tfgcoches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserMenu extends AppCompatActivity {
    TextView prueba;
    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        prueba = findViewById(R.id.tvUsuario);
        datos = getIntent().getExtras();
        prueba.setText(prueba.getText().toString() + " " + datos.getString("nombreUsuario"));

    }

    public void logOut(View v){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("nombreUsuario", datos.getString("nombreUsuario"));
        startActivity(i);
    }

    public void irConsultar(View v){
        Intent i = new Intent(this, ConsultarVehicullo.class);
        i.putExtra("nombreUsuario", datos.getString("nombreUsuario"));
        startActivity(i);
    }
    public void irInfo(View v){
        Intent i = new Intent(this, InfoUsuario.class);
        i.putExtra("nombreUsuario", datos.getString("nombreUsuario"));
        startActivity(i);
    }


    public void irAcerca(View v){
        Intent i = new Intent(this, AcercaEmpresa.class);
        i.putExtra("nombreUsuario", datos.getString("nombreUsuario"));
        startActivity(i);
    }
}
