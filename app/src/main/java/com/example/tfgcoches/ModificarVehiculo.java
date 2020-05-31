package com.example.tfgcoches;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
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

public class ModificarVehiculo extends AppCompatActivity{
    EditText mVehiculo, mKilometros, mDetalles, mCombustible, mPrecio, mEnlace;
    TextView mTipo;
    Button modificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_vehiculo);

        mVehiculo = findViewById(R.id.etVehiculoModi);
        mKilometros = findViewById(R.id.etKmModi);
        mDetalles = findViewById(R.id.etDetallesModi);
        mCombustible = findViewById(R.id.etCombusModi);
        mPrecio = findViewById(R.id.etPrecioModi);
        mTipo = findViewById(R.id.tvTipoModi);
        mEnlace = findViewById(R.id.etEnlaceModi);
        modificar = findViewById(R.id.btnModificar);

    }
    public void buscar(View v){
        AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "vehiculos", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();

        String nomVehiculo = mVehiculo.getText().toString();
        Cursor fila = baseDatos.rawQuery("SELECT kilometros, detalles, combustible, precio, enlace, tipo FROM vehiculos WHERE vehiculo = '" + nomVehiculo + "'", null);

        if (fila.moveToFirst()) {
            mKilometros.setText(fila.getString(0));
            mKilometros.setEnabled(true);
            mDetalles.setText(fila.getString(1));
            mDetalles.setEnabled(true);
            mCombustible.setText(fila.getString(2));
            mCombustible.setEnabled(true);
            mPrecio.setText(fila.getString(3));
            mPrecio.setEnabled(true);
            mEnlace.setText(fila.getString(4));
            mEnlace.setEnabled(true);
            mTipo.setText(fila.getString(5));
            modificar.setEnabled(true);
        } else{
            Toast.makeText(this, "No tenemos ese vehiculo disponible", Toast.LENGTH_LONG).show();
            limpiarText();
        }
        baseDatos.close();

    }

    public void limpiarText(){
        modificar.setEnabled(false);
        mKilometros.setText("");
        mKilometros.setEnabled(false);
        mDetalles.setText("");
        mDetalles.setEnabled(false);
        mCombustible.setText("");
        mCombustible.setEnabled(false);
        mPrecio.setText("");
        mPrecio.setEnabled(false);
        mEnlace.setText("");
        mEnlace.setEnabled(false);
        mTipo.setText("");
    }


    public void volver(View v){
        Intent i = new Intent(this, AdminMenu.class);
        startActivity(i);
    }

    public void modificar(View v){
            if(mVehiculo.getText().toString().isEmpty() || mKilometros.getText().toString().isEmpty() || mDetalles.getText().toString().isEmpty() || mCombustible.getText().toString().isEmpty() || mPrecio.getText().toString().isEmpty() || mEnlace.getText().toString().isEmpty()){
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.tituloModiMsg)
                        .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                accionModificar();
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                builder.create();
                builder.show();
            }//fin del else que comprueba que no esta vacio

    }//fin del modificar

    public void accionModificar(){
        AdminSQLiteOpenHealper admin = new AdminSQLiteOpenHealper(this, "vehiculos", null, 1);
        SQLiteDatabase baseDatos = admin.getReadableDatabase();

        ContentValues contenido=new ContentValues();
        contenido.put("kilometros", mKilometros.getText().toString());
        contenido.put("detalles", mDetalles.getText().toString());
        contenido.put("combustible", mCombustible.getText().toString());
        contenido.put("precio", mPrecio.getText().toString());
        contenido.put("enlace", mEnlace.getText().toString());

        baseDatos.update("vehiculos", contenido, "vehiculo='" + mVehiculo.getText().toString() + "'" , null);
        Toast.makeText(this, "Se ha modificado el vehículo "+ mVehiculo.getText().toString(), Toast.LENGTH_LONG).show();
        baseDatos.close();
    }

}
