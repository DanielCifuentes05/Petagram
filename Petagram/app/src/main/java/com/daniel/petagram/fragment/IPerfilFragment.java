package com.daniel.petagram.fragment;

import com.daniel.petagram.adapter.MascotaAdapter;
import com.daniel.petagram.adapter.PerfilAdapter;
import com.daniel.petagram.pojo.Mascota;

import java.util.ArrayList;

public interface IPerfilFragment {

    public void generarGridLayout();

    public PerfilAdapter crearAdapterP(ArrayList<Mascota> mascotas);

    public void inicializarAdapterPerfil(PerfilAdapter adapter);

}
