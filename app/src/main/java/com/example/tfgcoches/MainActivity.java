package com.example.tfgcoches;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usuario, contra;
    String[] datos1 = {"0001AAA", "Seat Leon", "1000", "Rojo", "Diesel", "10000", "https://www.seat.es/coches/nuevo-leon-2020/modelo.html", "Coche"};
    String[] datos2= {"0002AAA", "Yamaha TZ", "100", "Negra", "Gasolina", "8000", "https://www.motociclismo.es/pruebas/motos-clasicas/articulo/yamaha-tz-250-d-1977", "Moto"};
    String[] datos3 = {"0003AAA", "Scania Transport", "10000", "Gris", "Diesel", "25000", "https://www.scania.com/es/es/home/products-and-services/trucks.html", "Camion"};
    String[] datos4 = {"0004AAA", "Yate Luxury", "500", "Blanco", "Diesel", "50000", "https://www.topbarcos.com/barcos-ocasion-brokerage/yates_de_lujo", "Barco"};
    String[] datosU1 = {"00000000A", "admin", "admin", "jefe", "aqui"};
    String[] datosU2 = {"70594016R", "Manu", "contraseña", "Marvel", "C/Iron Man, 56, Los Angeles"};

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.etLoginUser);
        contra = findViewById(R.id.etLoginContra);

        cargarDatosPrueba(datos1);
        cargarDatosPrueba(datos2);
        cargarDatosPrueba(datos3);
        cargarDatosPrueba(datos4);
        cargarDatosUsuario(datosU1);
        cargarDatosUsuario(datosU2);

    }

    public void logIn(View v){
        Intent i;
        if(usuario.getText().toString().isEmpty() || contra.getText().toString().isEmpty())
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();

        AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "usuarios", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();
        Cursor fila = baseDatos.rawQuery("SELECT contrasena FROM usuarios WHERE usuario = '" + usuario.getText().toString() + "'", null);
        String contraUsuario = "";

        if(fila.moveToFirst()) {
            contraUsuario = fila.getString(0);
        }

        if(usuario.getText().toString().equals("admin") && contra.getText().toString().equals(contraUsuario)){
            i = new Intent(this, AdminMenu.class);
            startActivity(i);
        }

        if(!usuario.getText().toString().equals("admin") && !usuario.getText().toString().equals("") && contra.getText().toString().equals(contraUsuario)){
            i = new Intent(this, UserMenu.class);
            i.putExtra("nombreUsuario", usuario.getText().toString());
            startActivity(i);
        }
        baseDatos.close();

    }

    public void cargarDatosPrueba(String[] datos){
        AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "vehiculos", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();

        ContentValues contenido = new ContentValues();
        contenido.put("matricula", datos[0]);
        contenido.put("vehiculo", datos[1]);
        contenido.put("kilometros", datos[2]);
        contenido.put("detalles", datos[3]);
        contenido.put("combustible", datos[4]);
        contenido.put("precio", datos[5]);
        contenido.put("enlace", datos[6]);
        contenido.put("tipo", datos[7]);

        baseDatos.insert("vehiculos", null, contenido);
        baseDatos.close();
    }

    public void cargarDatosUsuario(String[] datos){
        AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "usuarios", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();

        ContentValues contenido = new ContentValues();
        contenido.put("dni", datos[0]);
        contenido.put("usuario", datos[1]);
        contenido.put("contrasena", datos[2]);
        contenido.put("productoraAsociada", datos[3]);
        contenido.put("direccionFiscal", datos[4]);

        baseDatos.insert("usuarios", null, contenido);
        baseDatos.close();
    }

    public void registrar(View v){
         Intent i = new Intent(this, RegistrarNuevoUsuario.class);
         startActivity(i);
    }
}
