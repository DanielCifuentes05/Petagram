package com.daniel.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.daniel.petagram.adapter.MascotaAdapter;
import com.daniel.petagram.db.BaseDatos;
import com.daniel.petagram.db.ConstructorMascotas;
import com.daniel.petagram.pojo.Mascota;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Mejores5 extends AppCompatActivity {

    ArrayList<Mascota> mascotas5 = new ArrayList<>();
    ArrayList<Mascota> mascotas= new ArrayList<>();
    private RecyclerView listaMascotas;
    Context context;
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
        obtenerMejores5();
        inicializarAdapter();
    }

    public void inicializarAdapter(){
        MascotaAdapter adapter = new MascotaAdapter(mascotas5,this);
        listaMascotas.setAdapter(adapter);

    }

    public void obtenerMejores5(){
        BaseDatos db = new BaseDatos(this);
        mascotas = db.obtenerMascotas();
        Collections.sort(mascotas);

        for(int i = mascotas.size()-1; i>= mascotas.size()-5; i--){
            mascotas5.add(mascotas.get(i));
        }

    }

}
