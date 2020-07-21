package com.daniel.petagram.fragment;

import com.daniel.petagram.adapter.MascotaAdapter;
import com.daniel.petagram.pojo.Mascota;

import java.util.ArrayList;

public interface IHomeFragment {

    public void generarLinearLayout();

    public MascotaAdapter crearAdapter(ArrayList<Mascota> mascotas);

    public void inicializarAdapterHome(MascotaAdapter adapter);



}
