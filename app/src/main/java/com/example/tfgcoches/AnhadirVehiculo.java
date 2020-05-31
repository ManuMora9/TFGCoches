package com.example.tfgcoches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AnhadirVehiculo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText matricula, vehiculo, kilometros, detalles, combustible, precio, enlace;
    Spinner tipo;
    String[] tipos = {"Seleccione", "Coche", "Moto", "Camión", "Barco"};
    String tipoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anhadir_vehiculo);

        matricula = findViewById(R.id.etMatricula);
        vehiculo = findViewById(R.id.etVehiculo);
        kilometros = findViewById(R.id.etKm);
        detalles = findViewById(R.id.etDetalles);
        combustible = findViewById(R.id.etCombus);
        precio = findViewById(R.id.etPrecio);
        enlace = findViewById(R.id.etEnlace);
        tipo = findViewById(R.id.spinnerTipo);
        tipoSeleccionado = "";

        tipo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tipos));
        tipo.setOnItemSelectedListener(this);
    }

    public void volver(View v){
        Intent i = new Intent(this, AdminMenu.class);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tipoSeleccionado = tipos[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void anhadir(View v){
        if(matricula.getText().toString().isEmpty() || vehiculo.getText().toString().isEmpty() || kilometros.getText().toString().isEmpty() || detalles.getText().toString().isEmpty() || combustible.getText().toString().isEmpty() || precio.getText().toString().isEmpty() || enlace.getText().toString().isEmpty() || tipoSeleccionado.equals("") || tipoSeleccionado.equals("Seleccione")){
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();
        }
        else{
            AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "vehiculos", null, 1);
            SQLiteDatabase baseDatos = admin.getWritableDatabase();

            String datMatri = matricula.getText().toString();
            String datVehi = vehiculo.getText().toString();
            String datKilo = kilometros.getText().toString();
            String datDeta = detalles.getText().toString();
            String datCombus = combustible.getText().toString();
            String datPrecio = precio.getText().toString();
            String datEnlace = enlace.getText().toString();
            String datTipo = tipoSeleccionado;

            ContentValues contenido = new ContentValues();
            contenido.put("matricula", datMatri);
            contenido.put("vehiculo", datVehi);
            contenido.put("kilometros", datKilo);
            contenido.put("detalles", datDeta);
            contenido.put("combustible", datCombus);
            contenido.put("precio", datPrecio);
            contenido.put("enlace", datEnlace);
            contenido.put("tipo", datTipo);

            baseDatos.insert("vehiculos", null, contenido);
            Toast.makeText(this, "Vehiculo insertado", Toast.LENGTH_LONG).show();
            matricula.setText("");
            vehiculo.setText("");
            kilometros.setText("");
            detalles.setText("");
            combustible.setText("");
            precio.setText("");
            enlace.setText("");
            tipo.setSelection(0);

            baseDatos.close();
        }
    }
}
