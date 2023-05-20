package com.example.listacompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ingreso extends AppCompatActivity {

    Button lista;
    Button configuracion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        lista = findViewById(R.id.lista_compras);
        configuracion = findViewById(R.id.btn_Configura);

        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ingreso.this, SettingsActivity.class);
                startActivity(i);
            }
        });

        lista.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ingreso.this, Lista.class);
                startActivity(i);
            }
        });
    }
}