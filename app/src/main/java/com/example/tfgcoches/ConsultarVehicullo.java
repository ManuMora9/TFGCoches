package com.example.tfgcoches;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConsultarVehicullo extends AppCompatActivity {
    TextView conKm, conDetalles, conCombus, conPrecio, conTipo;
    Button visitar;
    EditText vehiculo;

    String direccion;
    Bundle intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_vehicullo);

        conKm = findViewById(R.id.tvConsulKM);
        conDetalles = findViewById(R.id.tvConsulDetalles);
        conCombus = findViewById(R.id.tvConsulCombus);
        conPrecio = findViewById(R.id.tvConsulPrecio);
        conTipo = findViewById(R.id.tvConsulTipo);
        vehiculo = findViewById(R.id.etVehiculo);
        visitar = findViewById(R.id.btnVisitar);

        direccion = "";
        intent = getIntent().getExtras();
    }

    public void buscar(View v){
        AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "vehiculos", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();

        String nomVehiculo = vehiculo.getText().toString();
        Cursor fila = baseDatos.rawQuery("SELECT kilometros, detalles, combustible, precio, enlace, tipo FROM vehiculos WHERE vehiculo = '" + nomVehiculo + "'", null);

        if (fila.moveToFirst()) {
           conKm.setText(fila.getString(0));
            conDetalles.setText(fila.getString(1));
            conCombus.setText(fila.getString(2));
            conPrecio.setText(fila.getString(3));
            direccion = fila.getString(4);
            conTipo.setText(fila.getString(5));
            visitar.setEnabled(true);
        } else{
            Toast.makeText(this, "No tenemos ese vehiculo disponible", Toast.LENGTH_LONG).show();
            visitar.setEnabled(false);
            conKm.setText("");
            conDetalles.setText("");
            conCombus.setText("");
            conPrecio.setText("");
            direccion = "";
            conTipo.setText("");
        }
        baseDatos.close();

    }

    public void volverUser(View v){
        Intent i = new Intent(this, UserMenu.class);
        i.putExtra("nombreUsuario", intent.getString("nombreUsuario"));
        startActivity(i);
    }

    public void visitarWeb(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(direccion));
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.error)
                    .setPositiveButton(R.string.volver, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.create();
                    builder.show();
        }
    }

}
