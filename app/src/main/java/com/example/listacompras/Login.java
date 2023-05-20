package com.example.listacompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("1234")){
                    Toast.makeText(Login.this, "Ingreso Correcto", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Ingreso.class);
                    startActivity(i);
                } else{
                    Toast.makeText(Login.this, "Ingreso Erroneo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}