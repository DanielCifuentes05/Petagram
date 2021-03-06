package com.daniel.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.daniel.petagram.adapter.MascotaAdapter;
import com.daniel.petagram.db.BaseDatos;
import com.daniel.petagram.db.ConstructorMascotas;
import com.daniel.petagram.pojo.Mascota;
import com.daniel.petagram.pojo.Media;
import com.daniel.petagram.restAPI.ConstantesRestApi;
import com.daniel.petagram.restAPI.EndpointsApi;
import com.daniel.petagram.restAPI.adapter.RestApiAdapter;
import com.daniel.petagram.restAPI.model.MascotaResponse;
import com.daniel.petagram.restAPI.model.MediaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mejores5 extends AppCompatActivity {

    ArrayList<Mascota> mascotas5 = new ArrayList<>();
    ArrayList<Mascota> mascotas= new ArrayList<>();
    private RecyclerView listaMascotas;
    private ArrayList<Media> media= new ArrayList<>();
    private ArrayList<Mascota> mascotaTemp= new ArrayList<>();
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
        obtenerMediaIds();

    }

    public void inicializarAdapter(){
        MascotaAdapter adapter = new MascotaAdapter(mascotas5,this);
        listaMascotas.setAdapter(adapter);

    }

    public void obtenerMejores5(){
        Collections.sort(mascotas);

        for(int i = mascotas.size()-1; i >= mascotas.size()-5; i--){
            mascotas5.add(mascotas.get(i));
        }
        inicializarAdapter();

    }


    public void obtenerMediosMascotaRecientes() {
        for (int i = 0; i < media.size() ; i++) {

            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Gson gsonMascota = restApiAdapter.construyeGsonMascota();
            EndpointsApi endpointsApi = restApiAdapter.establecerConexionApiInstagram(gsonMascota);
            final Call<MascotaResponse> MascotaResponseCall = endpointsApi.getRecentMedia(media.get(i).getId()+ ConstantesRestApi.GET_MEDIA_DATA);
            MascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
                @Override
                public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                    MascotaResponse mascotaResponse= response.body();
                    mascotaTemp = mascotaResponse.getMascotas();
                    mascotas.add(mascotaTemp.get(0));
                    //mostrarMascotasRV();
                    if(mascotas.size()==5){
                        obtenerMejores5();

                    }
                }

                @Override
                public void onFailure(Call<MascotaResponse> call, Throwable t) {

                    Toast.makeText(context, "Ocurrio un error, intenta de nuevo", Toast.LENGTH_SHORT).show();
                    Log.e("Fallo peticion mascota", t.toString() );

                }
            });
        }

    }


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

    }

}