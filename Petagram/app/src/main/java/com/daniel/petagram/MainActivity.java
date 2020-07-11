package com.daniel.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mytoolbar=(Toolbar) findViewById(R.id.miToolbar);
        setSupportActionBar(mytoolbar);

        ImageButton cinco = (ImageButton) findViewById(R.id.cincoMejores);

        listaMascotas=(RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
        llenarLista();
        inicializarAdapter();
        myFab();

        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Cinco", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(MainActivity.this, mejores5.class);
                startActivity(intent);
            }
        });

    }

    public void inicializarAdapter(){
        MascotaAdapter adapter = new MascotaAdapter(mascotas,this);
        listaMascotas.setAdapter(adapter);

    }

    public void llenarLista(){

        mascotas = new ArrayList<>();

        mascotas.add(new Mascota("Lupe","Gato","Hembra",3, R.drawable.gato3));
        mascotas.add(new Mascota("Max","Perro", "Macho",5, R.drawable.mascota_mosquito_1));
        mascotas.add(new Mascota("Rufo","Perro", "Macho",7, R.drawable.perrito1));
        mascotas.add(new Mascota("Hana","Perro", "Hembra",6, R.drawable.perro));
        mascotas.add(new Mascota("Fiona","Perro", "Hembra",9, R.drawable.perro6));
        mascotas.add(new Mascota("Aron","Gato", "Macho",6, R.drawable.gato));
    }

    public void myFab(){
        FloatingActionButton Fab = (FloatingActionButton) findViewById(R.id.myFAB);
        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), getResources().getString(R.string.toast), Toast.LENGTH_SHORT).show();
            }
        });
    }
}