package com.example.listacompras.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.listacompras.Entity.Productos;

import java.util.ArrayList;

public class BdProductos extends BdHelper{
    Context context;

    public BdProductos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProductos(String nombre, String cantidad, String categoria) {
        long id = 0;
        try {
            BdHelper bdHelper = new BdHelper(context);
            SQLiteDatabase bd = bdHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("cantidad", cantidad);
            values.put("categoria", categoria);
            id = bd.insert(TABLE_PRODUCTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Productos> mostrarProductos() {
        BdHelper bdHelper = new BdHelper(context);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        ArrayList<Productos> listaProductos = new ArrayList<>();
        Productos producto = null;
        Cursor cursorProductos = null;
        cursorProductos = bd.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS, null);

        if (cursorProductos.moveToFirst()) {
            do {
                producto = new Productos();
                producto.setId(cursorProductos.getInt(0));
                producto.setNombre(cursorProductos.getString(1));
                producto.setCantidad(cursorProductos.getString(2));
                producto.setCategoria(cursorProductos.getString(3));
                listaProductos.add(producto);
            } while (cursorProductos.moveToNext());
        }
        cursorProductos.close();
        return listaProductos;
    }

    public Productos verProductos(int id) {
        BdHelper bdHelper = new BdHelper(context);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        Productos producto = null;
        Cursor cursorProductos;

        cursorProductos = bd.rawQuery("SELECT * FROM " + TABLE_PRODUCTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorProductos.moveToFirst()) {
            producto = new Productos();
            producto.setId(cursorProductos.getInt(0));
            producto.setNombre(cursorProductos.getString(1));
            producto.setCantidad(cursorProductos.getString(2));
            producto.setCategoria(cursorProductos.getString(3));

        }
        cursorProductos.close();


        return producto;
    }

    public boolean editarProductos(int id, String nombre, String cantidad, String categoria) {
        boolean correcto = false;

        BdHelper bdHelper = new BdHelper(context);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        try {
            bd.execSQL("UPDATE " + TABLE_PRODUCTOS + " SET nombre = '"+ nombre +"', cantidad = '"+ cantidad +"', categoria = '"+ categoria + "' WHERE id = '" + id + "'");
            correcto =  true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        }finally{
            bd.close();
        }
        return correcto;
    }

    public boolean eliminarProductos(int id) {
        boolean correcto = false;

        BdHelper bdHelper = new BdHelper(context);
        SQLiteDatabase bd = bdHelper.getWritableDatabase();

        try {
            bd.execSQL("DELETE FROM " + TABLE_PRODUCTOS + " WHERE id = '" + id + "'");
            correcto =  true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        }finally{
            bd.close();
        }
        return correcto;
    }
}
