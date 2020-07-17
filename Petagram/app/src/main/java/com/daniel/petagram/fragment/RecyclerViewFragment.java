package com.daniel.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.petagram.R;
import com.daniel.petagram.adapter.MascotaAdapter;
import com.daniel.petagram.pojo.Mascota;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {


    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);


        listaMascotas =(RecyclerView) v.findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);

        llenarLista();
        inicializarAdapter();
        return v;

    }

    public void inicializarAdapter(){
        MascotaAdapter adapter = new MascotaAdapter(mascotas,getActivity());
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
}
