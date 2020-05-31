package com.example.tfgcoches;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BorrarVehiculo extends AppCompatActivity {
    TextView bKilometros, bDetalles, bCombustible, bPrecio, bTipo, bEnlace;
    EditText bVehiculo;
    Button borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_vehiculo);

        bVehiculo = findViewById(R.id.etBorrarVehi);
        bKilometros = findViewById(R.id.tvBorrarKm);
        bDetalles = findViewById(R.id.tvBorrarDetalles);
        bCombustible = findViewById(R.id.tvBorrarCombus);
        bPrecio = findViewById(R.id.tvBorrarPrecio);
        bEnlace = findViewById(R.id.tvBorrarEnlace);
        bTipo = findViewById(R.id.tvBorrarTipo);
        borrar = findViewById(R.id.btnBorrar);

    }
    public void buscar(View v){
        AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "vehiculos", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();

        String nomVehiculo = bVehiculo.getText().toString();
        Cursor fila = baseDatos.rawQuery("SELECT kilometros, detalles, combustible, precio, enlace, tipo FROM vehiculos WHERE vehiculo = '" + nomVehiculo + "'", null);

        if (fila.moveToFirst()) {
            bKilometros.setText(fila.getString(0));
            bDetalles.setText(fila.getString(1));
            bCombustible.setText(fila.getString(2));
            bPrecio.setText(fila.getString(3));
            bEnlace.setText(fila.getString(4));
            bTipo.setText(fila.getString(5));
            borrar.setEnabled(true);
        } else{
            Toast.makeText(this, "No tenemos ese vehiculo disponible", Toast.LENGTH_LONG).show();
            limpiarText();
        }
        baseDatos.close();

    }


    public void volver(View v){
        Intent i = new Intent(this, AdminMenu.class);
        startActivity(i);
    }

    public void accionBorrar(){
        AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "vehiculos", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();

        baseDatos.delete("vehiculos", "vehiculo='"+bVehiculo.getText().toString()+"'", null);
        Toast.makeText(this, "He borrado el vehiculo "+  bVehiculo.getText().toString(), Toast.LENGTH_LONG).show();
    }

    public void borrar(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.tituloBorrarMsg)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            accionBorrar();
                            limpiarText();
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

            builder.create();
            builder.show();
    }//fin del metodo borrar

    public void limpiarText(){
        borrar.setEnabled(false);
        bKilometros.setText("");
        bDetalles.setText("");
        bCombustible.setText("");
        bPrecio.setText("");
        bEnlace.setText("");
        bTipo.setText("");
    }
}
