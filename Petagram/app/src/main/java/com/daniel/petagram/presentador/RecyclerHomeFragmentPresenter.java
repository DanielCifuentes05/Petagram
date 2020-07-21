package com.daniel.petagram.presentador;

import android.content.Context;

import com.daniel.petagram.adapter.MascotaAdapter;
import com.daniel.petagram.db.ConstructorMascotas;
import com.daniel.petagram.fragment.IHomeFragment;
import com.daniel.petagram.pojo.Mascota;

import java.util.ArrayList;

public class RecyclerHomeFragmentPresenter implements IRecyclerHomeFragmentPresenter {

    private IHomeFragment iHomeFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerHomeFragmentPresenter(IHomeFragment iHomeFragment, Context context) {
        this.iHomeFragment = iHomeFragment;
        this.context= context;
        obtenerMascotasBD();
    }


    @Override
    public void obtenerMascotasBD() {
        constructorMascotas= new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();

    }

    @Override
    public void mostrarMascotasRV() {
        iHomeFragment.inicializarAdapterHome(iHomeFragment.crearAdapter(mascotas));
        iHomeFragment.generarLinearLayout();

    }
}
