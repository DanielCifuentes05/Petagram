package com.daniel.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.messaging.FirebaseMessaging;

public class ConfigurarCuenta extends AppCompatActivity {


    Button guardar;
    TextInputLayout nombreUsuario;
    private String token;

    private static final String TAG = "ConfigurarCuenta";
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
                //Toast.makeText(getBaseContext(), "Función deshabilitada", Toast.LENGTH_LONG).show();

                Intent intent= new Intent(ConfigurarCuenta.this,MainActivity.class);
                intent.putExtra(getResources().getString(R.string.clvCuenta), nombreUsuario.getEditText().getText().toString());
                startActivity(intent);
                finish();

                Toast.makeText(getBaseContext(), "Cuenta Guardada: " +  nombreUsuario.getEditText().getText().toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

}