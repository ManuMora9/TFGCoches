package com.example.tfgcoches;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHealper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHealper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table vehiculos (matricula varchar(50) primary key, vehiculo varchar(50), kilometros int, detalles varchar(50), combustible varchar(50), precio int, enlace varchar(100), tipo varchar(50))");
        db.execSQL("create table usuarios (dni varchar(50) primary key, usuario varchar(50), contrasena varchar(50), productoraAsociada varchar(50), direccionFiscal varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
