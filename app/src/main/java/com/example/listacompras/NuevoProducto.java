package com.example.listacompras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listacompras.BaseDatos.BdProductos;

public class NuevoProducto extends AppCompatActivity {


    EditText txtNombre, txtCantidad, txtCategoria;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);

        txtNombre = findViewById(R.id.txtNombre);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtCategoria = findViewById(R.id.txtCategoria);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BdProductos bdProductos = new BdProductos(NuevoProducto.this);
                long id = bdProductos.insertarProductos(txtNombre.getText().toString(), txtCantidad.getText().toString(),txtCategoria.getText().toString());

                if( id >0){
                    Toast.makeText(NuevoProducto.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                } else {
                    Toast.makeText(NuevoProducto.this, "EROR AL GUARDAR", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void limpiar (){
        txtNombre.setText("");
        txtCantidad.setText("");
        txtCategoria.setText("");
    }
}