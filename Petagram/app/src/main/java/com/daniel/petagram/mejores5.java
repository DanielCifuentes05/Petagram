package com.daniel.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.daniel.petagram.adapter.MascotaAdapter;
import com.daniel.petagram.pojo.Mascota;

import java.util.ArrayList;

public class Mejores5 extends AppCompatActivity {

    ArrayList<Mascota> mascotas5;
    private RecyclerView listaMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mejores5);

        Toolbar mytoolbar=(Toolbar) findViewById(R.id.miToolbar2);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listaMascotas=(RecyclerView) findViewById(R.id.rvMascotas2);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        llenarLista();
        inicializarAdapter();
    }

    public void inicializarAdapter(){
        MascotaAdapter adapter = new MascotaAdapter(mascotas5,this);
        listaMascotas.setAdapter(adapter);

    }
    public void llenarLista(){

        mascotas5 = new ArrayList<>();

        //mascotas.add(new Mascota("Lupe","Gato","Hembra",3, R.drawable.gato3));
        mascotas5.add(new Mascota("Max","Perro", "Macho",5, R.drawable.mascota_mosquito_1));
        mascotas5.add(new Mascota("Rufo","Perro", "Macho",7, R.drawable.perrito1));
        mascotas5.add(new Mascota("Hana","Perro", "Hembra",6, R.drawable.perro));
        mascotas5.add(new Mascota("Fiona","Perro", "Hembra",9, R.drawable.perro6));
        mascotas5.add(new Mascota("Aron","Gato", "Macho",6, R.drawable.gato));
    }

}