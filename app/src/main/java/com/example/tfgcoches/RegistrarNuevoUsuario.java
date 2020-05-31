package com.example.tfgcoches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarNuevoUsuario extends AppCompatActivity {
    EditText dni, usuario, contra, repeContra, productora, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_nuevo_usuario);

        dni = findViewById(R.id.etDNI);
        usuario = findViewById(R.id.etUsuario);
        contra = findViewById(R.id.etContra);
        repeContra = findViewById(R.id.etRepetirContra);
        productora = findViewById(R.id.etProductora);
        direccion = findViewById(R.id.etDireccion);
    }

    public void volver(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void darAlta(View v){
        if(dni.getText().toString().isEmpty() || usuario.getText().toString().isEmpty() || contra.getText().toString().isEmpty() || repeContra.getText().toString().isEmpty() || productora.getText().toString().isEmpty() || direccion.getText().toString().isEmpty())
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();

        else if(!contra.getText().toString().equals(repeContra.getText().toString()))
            Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_LONG).show();

        else{
            AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "usuarios", null, 1);
            SQLiteDatabase baseDatos = admin.getWritableDatabase();

            ContentValues contenido = new ContentValues();
            contenido.put("dni", dni.getText().toString());
            contenido.put("usuario", usuario.getText().toString());
            contenido.put("contrasena", contra.getText().toString());
            contenido.put("productoraAsociada", productora.getText().toString());
            contenido.put("direccionFiscal", direccion.getText().toString());

            baseDatos.insert("usuarios", null, contenido);
            baseDatos.close();

            Intent i = new Intent(this, InfoUsuario.class);
            i.putExtra("nombreUsuario", usuario.getText().toString());
            startActivity(i);
        }

    }
}
