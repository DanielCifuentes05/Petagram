package com.daniel.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daniel.petagram.mail.JavaMailAPI;
import com.google.android.material.textfield.TextInputLayout;

public class Formulario extends AppCompatActivity {

    Button enviar;
    TextInputLayout nombre;
    TextInputLayout email;
    TextInputLayout mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        enviar=(Button) findViewById(R.id.btnEnviar);
        nombre=(TextInputLayout) findViewById(R.id.tiNombre);
        email=(TextInputLayout) findViewById(R.id.tiEmail);
        mensaje=(TextInputLayout) findViewById(R.id.tiMensaje);

        Toolbar mytoolbar=(Toolbar) findViewById(R.id.miToolbar2);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Enviado", Toast.LENGTH_SHORT).show();

                String subject  = nombre.getEditText().getText().toString();
                String mail     = email.getEditText().getText().toString();
                String body     = mensaje.getEditText().getText().toString();

                JavaMailAPI sendmail = new JavaMailAPI(Formulario.this, mail, subject , body);
                sendmail.execute();

            }
        });
    }

}