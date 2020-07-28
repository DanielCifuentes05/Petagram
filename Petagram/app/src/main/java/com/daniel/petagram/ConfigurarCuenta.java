package com.daniel.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ConfigurarCuenta extends AppCompatActivity {


    Button guardar;
    TextInputLayout nombreUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        guardar=(Button) findViewById(R.id.btnGuardar);
        nombreUsuario=(TextInputLayout) findViewById(R.id.tiNombreUsuario);

        Toolbar mytoolbar=(Toolbar) findViewById(R.id.miToolbar2);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Función deshabilitada", Toast.LENGTH_LONG).show();
            }
        });
    }
}