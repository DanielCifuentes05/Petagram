package com.daniel.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.daniel.petagram.db.ConstructorMascotas;
import com.daniel.petagram.fragment.IPerfilFragment;
import com.daniel.petagram.pojo.Mascota;
import com.daniel.petagram.restAPI.EndpointsApi;
import com.daniel.petagram.restAPI.adapter.RestApiAdapter;
import com.daniel.petagram.restAPI.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {

    private IPerfilFragment iPerfilFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas = new ArrayList<>();



    public PerfilFragmentPresenter(IPerfilFragment iPerfilFragment, Context context) {
        this.iPerfilFragment = iPerfilFragment;
        this.context= context;
        //obtenerMascotasPerfilBD();
        obtenerMediosPerfilRecientes();
    }



    @Override
    public void obtenerMediosPerfilRecientes() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMascota = restApiAdapter.construyeGsonMascota();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionApiInstagram(gsonMascota);
        final Call<MascotaResponse> MascotaResponseCall = endpointsApi.getMediaId();
        MascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse= response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasPerfilRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error, intenta de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Fallo peticion mascota", t.toString() );

            }
        });

    }


    @Override
    public void mostrarMascotasPerfilRV() {
        iPerfilFragment.inicializarAdapterPerfil(iPerfilFragment.crearAdapterP(mascotas));
        iPerfilFragment.generarGridLayout();

    }
}
