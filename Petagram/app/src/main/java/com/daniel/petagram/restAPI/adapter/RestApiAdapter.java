package com.daniel.petagram.restAPI.adapter;

import com.daniel.petagram.restAPI.ConstantesRestApi;
import com.daniel.petagram.restAPI.EndpointsApi;
import com.daniel.petagram.restAPI.deserializador.MascotaDeserializador;
import com.daniel.petagram.restAPI.deserializador.MediaDeserializador;
import com.daniel.petagram.restAPI.model.MascotaResponse;
import com.daniel.petagram.restAPI.model.MediaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public EndpointsApi establecerConexionApiInstagram(Gson gson ){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonMedia(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MediaResponse.class, new MediaDeserializador());

         return gsonBuilder.create();


    }

    public Gson construyeGsonMascota(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());

        return gsonBuilder.create();


    }
}
