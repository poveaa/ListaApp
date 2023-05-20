package com.example.listacompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.listacompras.Adapter.ProductosAdapter;
import com.example.listacompras.BaseDatos.BdProductos;
import com.example.listacompras.Entity.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Lista extends AppCompatActivity implements SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {

    android.widget.SearchView txtBuscar;
    RecyclerView listaProductos;
    ArrayList<Productos> listaArrayProductos;
    FloatingActionButton floatAdd;
   ProductosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        txtBuscar = findViewById(R.id.txtBuscar);
        listaProductos = findViewById(R.id.listaProductos);
        floatAdd = findViewById(R.id.floatAdd);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        BdProductos bdProductos = new BdProductos(Lista.this);

        listaArrayProductos = new ArrayList<>();
        adapter = new ProductosAdapter(bdProductos.mostrarProductos());
        listaProductos.setAdapter(adapter);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nuevoRegistro();
            }
        });

        txtBuscar.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_inicio, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.nuevoMenu:
                nuevoRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoProducto.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrar(s);
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}