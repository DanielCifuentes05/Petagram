package com.daniel.petagram.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.petagram.R;
import com.daniel.petagram.adapter.MascotaAdapter;
import com.daniel.petagram.adapter.PerfilAdapter;
import com.daniel.petagram.pojo.Mascota;

import java.util.ArrayList;


public class PerfilFragment extends Fragment {

    ArrayList<Integer> fotos;
    ArrayList<Integer> likes;
    private RecyclerView gridPerfil;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_perfil, container, false);
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        gridPerfil =(RecyclerView) v.findViewById(R.id.rvPerfil);
        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        gridPerfil.setLayoutManager(glm);

        llenarLista();
        inicializarAdapter();
        return v;
    }

    public void inicializarAdapter(){
        PerfilAdapter adapter = new PerfilAdapter(fotos,likes,getActivity());
        gridPerfil.setAdapter(adapter);

    }

    public void llenarLista(){

        fotos = new ArrayList<>();
        likes = new ArrayList<>();

        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);
        fotos.add(R.drawable.perrito1);

        likes.add(21);
        likes.add(19);
        likes.add(27);
        likes.add(31);
        likes.add(32);
        likes.add(52);
        likes.add(32);
        likes.add(56);
        likes.add(1);
        likes.add(3);
        likes.add(14);
        likes.add(532);
        likes.add(12);
        likes.add(9);
        likes.add(45);
        likes.add(23);
        likes.add(34);
        likes.add(4);

    }


}