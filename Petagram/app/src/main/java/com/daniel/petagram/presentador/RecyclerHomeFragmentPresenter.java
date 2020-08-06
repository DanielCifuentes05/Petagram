package com.daniel.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.daniel.petagram.db.ConstructorMascotas;
import com.daniel.petagram.fragment.IHomeFragment;
import com.daniel.petagram.pojo.Mascota;
import com.daniel.petagram.restAPI.EndpointsApi;
import com.daniel.petagram.restAPI.adapter.RestApiAdapter;
import com.daniel.petagram.restAPI.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerHomeFragmentPresenter implements IRecyclerHomeFragmentPresenter {

    private IHomeFragment iHomeFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas = new ArrayList<>();


    public RecyclerHomeFragmentPresenter(IHomeFragment iHomeFragment, Context context) {
        this.iHomeFragment = iHomeFragment;
        this.context= context;
        //obtenerMascotasBD();
        obtenerMediosMascotaRecientes();
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

    @Override
    public void obtenerMediosMascotaRecientes() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMascota = restApiAdapter.construyeGsonMascota();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionApiInstagram(gsonMascota);
        final Call<MascotaResponse> MascotaResponseCall = endpointsApi.getMediaId();
        MascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse= response.body();
                mascotas= mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error, intenta de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Fallo peticion mascota", t.toString() );

            }
        });
    }

    /*@Override
    public void obtenerMediaIds() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMedia = restApiAdapter.construyeGsonMedia();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionApiInstagram(gsonMedia);
        Call<MediaResponse> MediaResponseCall = endpointsApi.getMediaId();

        MediaResponseCall.enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {

                MediaResponse mediaResponse= response.body();
                media = mediaResponse.getMedia();
                obtenerMediosMascotaRecientes();

            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {

                Toast.makeText(context, "Ocurrio un error, intenta de nuevo", Toast.LENGTH_SHORT).show();
                Log.e("Fallo peticion media", t.toString() );

            }
        });

    }*/
}
