package com.example.listacompras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.listacompras.Entity.Productos;
import com.example.listacompras.BaseDatos.BdProductos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarProductos extends AppCompatActivity {

    FloatingActionButton floatEditar, floatEliminar;
    EditText txtNombre, txtCantidad, txtCategoria;
    Button btnGuarda;
    Productos producto;

    boolean correcto  = false;
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
        floatEditar.setVisibility(View.INVISIBLE);
        floatEliminar = findViewById(R.id.floatEliminar);
        floatEliminar.setVisibility(View.INVISIBLE);


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

        BdProductos bdProductos = new BdProductos(EditarProductos.this);
        producto = bdProductos.verProductos(id);

        if (producto != null){
            txtNombre.setText(producto.getNombre());
            txtCantidad.setText(producto.getCantidad());
            txtCategoria.setText(producto.getCategoria());
        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && txtCantidad.getText().toString().equals("")){
                    bdProductos.editarProductos(id, txtNombre.getText().toString(), txtCantidad.getText().toString(), txtCategoria.getText().toString());

                    if (correcto){
                        Toast.makeText(EditarProductos.this, "REGISTRO CAMBIADO", Toast.LENGTH_SHORT).show();
                        verRegistros();
                    }else{
                        Toast.makeText(EditarProductos.this, "ERROR  AL CAMBIAR REGISTRO", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(EditarProductos.this, "DEBE LLENAR CAMPOS", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void verRegistros(){
        Intent intent = new Intent(this, VerProducto.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
