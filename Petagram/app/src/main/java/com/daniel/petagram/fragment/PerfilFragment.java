package com.daniel.petagram.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daniel.petagram.R;
import com.daniel.petagram.adapter.MascotaAdapter;
import com.daniel.petagram.adapter.PerfilAdapter;
import com.daniel.petagram.pojo.Mascota;
import com.daniel.petagram.presentador.IPerfilFragmentPresenter;
import com.daniel.petagram.presentador.PerfilFragmentPresenter;
import com.daniel.petagram.presentador.RecyclerHomeFragmentPresenter;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PerfilFragment extends Fragment implements IPerfilFragment {

    ArrayList<Integer> fotos;
    ArrayList<Integer> likes;
    private RecyclerView gridPerfil;
    private IPerfilFragmentPresenter presenter;
    CircularImageView fotoPerfil;
    TextView nombrePerfil;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_perfil, container, false);
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        gridPerfil =(RecyclerView) v.findViewById(R.id.rvPerfil);
        presenter= new PerfilFragmentPresenter(this, getContext());
        return v;
    }


    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        gridPerfil.setLayoutManager(glm);
    }

    @Override
    public PerfilAdapter crearAdapterP(ArrayList<Mascota> mascotas) {
        PerfilAdapter adapter = new PerfilAdapter(mascotas,getActivity());
        fotoPerfil = (CircularImageView) getActivity().findViewById(R.id.perfilFoto);
        nombrePerfil = (TextView) getActivity().findViewById(R.id.nombrePerfil);
        nombrePerfil.setText(mascotas.get(0).getNombre());
        Picasso.with(getActivity())
                .load(mascotas.get(0).getFoto())
                .placeholder(R.drawable.mascota_mosquito_1)
                .into(fotoPerfil);
        return adapter;
    }

    @Override
    public void inicializarAdapterPerfil(PerfilAdapter adapter) {
        gridPerfil.setAdapter(adapter);
    }




}