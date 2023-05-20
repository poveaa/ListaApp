package com.example.listacompras.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BdHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "tienda.db"; //nombre de la base de datos
    public static final String TABLE_PRODUCTOS = "t_productos"; //tabla de los productos



    public BdHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_PRODUCTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL," +
                "cantidad INTEGER NOT NULL," +
                "categoria TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //se ejecutará cuando se cambie la version de la base de datos
        sqLiteDatabase.execSQL(("DROP TABLE " + TABLE_PRODUCTOS));
        onCreate(sqLiteDatabase);
    }
}
