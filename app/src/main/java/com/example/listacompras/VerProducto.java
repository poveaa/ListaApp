package com.example.listacompras;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listacompras.BaseDatos.BdProductos;
import com.example.listacompras.Entity.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerProducto extends AppCompatActivity {

    EditText txtNombre, txtCantidad, txtCategoria;
    Button btnGuarda;
    FloatingActionButton floatEditar, floatEliminar;

    Productos producto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_producto);

        txtNombre = findViewById(R.id.txtNombre);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtCategoria = findViewById(R.id.txtCategoria);
        btnGuarda = findViewById(R.id.btnGuardar);
        floatEditar = findViewById(R.id.floatEditar);
        floatEliminar = findViewById(R.id.floatEliminar);


        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        BdProductos bdProductos = new BdProductos(VerProducto.this);
        producto = bdProductos.verProductos(id);

        if (producto != null){
            txtNombre.setText(producto.getNombre());
            txtCantidad.setText(producto.getCantidad());
            txtCategoria.setText(producto.getCategoria());
            btnGuarda.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtCantidad.setInputType(InputType.TYPE_NULL);
            txtCategoria.setInputType(InputType.TYPE_NULL);
        }
        floatEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerProducto.this, EditarProductos.class );
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        floatEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerProducto.this);
                builder.setMessage("¿Eliminar producto?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(bdProductos.eliminarProductos(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista (){
        Intent intent = new Intent(this, Lista.class);
        startActivity(intent);
    }
}
