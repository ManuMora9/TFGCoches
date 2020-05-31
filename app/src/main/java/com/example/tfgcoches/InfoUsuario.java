package com.example.tfgcoches;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class InfoUsuario extends AppCompatActivity {
    Bundle intent;
    TextView dni, usuario, productora, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);

        dni = findViewById(R.id.tvClave2);
        usuario = findViewById(R.id.tvNUsuario);
        productora = findViewById(R.id.tvProduc);
        direccion = findViewById(R.id.tvDireccion);

        intent = getIntent().getExtras();

        sacarInformacion();

    }

    public void sacarInformacion(){
        AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "usuarios", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();
        Cursor fila = baseDatos.rawQuery("SELECT dni, usuario, productoraAsociada, direccionFiscal FROM usuarios WHERE usuario = '" + intent.getString("nombreUsuario") + "'", null);

        if(fila.moveToFirst()){
            dni.setText(fila.getString(0));
            usuario.setText(fila.getString(1));
            productora.setText(fila.getString(2));
            direccion.setText(fila.getString(3));
        }

        baseDatos.close();
    }

    public void volverUser(View v){
        Intent i = new Intent(this, UserMenu.class);
        i.putExtra("nombreUsuario", intent.getString("nombreUsuario"));
        startActivity(i);
    }

    public void borrarUsuario(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.msgBorrarUser)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        accionBorrar();
                        Intent volver = new Intent(InfoUsuario.this, MainActivity.class);
                        startActivity(volver);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        builder.create();
        builder.show();
    }

    public void accionBorrar(){
        AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "usuarios", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();

        baseDatos.delete("usuarios", "usuario='" + intent.getString("nombreUsuario") + "'", null);
        baseDatos.close();
    }
}
