package com.daniel.petagram.restAPI.deserializador;

import com.daniel.petagram.pojo.Mascota;
import com.daniel.petagram.pojo.Media;
import com.daniel.petagram.restAPI.JsonKeys;
import com.daniel.petagram.restAPI.model.MascotaResponse;
import com.daniel.petagram.restAPI.model.MediaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonObject mascotaResponseData = json.getAsJsonObject();

        mascotaResponse.setMascotas(deserializarMascota(mascotaResponseData));
        return mascotaResponse;
    }

    public ArrayList<Mascota> deserializarMascota(JsonObject mascotaResponseData){

        Random rand = new Random();

        String id = mascotaResponseData.get(JsonKeys.ID).getAsString();
        String urlFoto = mascotaResponseData.get(JsonKeys.MEDIA_URL).getAsString();
        String username = mascotaResponseData.get(JsonKeys.USER_NAME).getAsString();
        int likes = rand.nextInt(25);

        Mascota mascotaActual = new Mascota();
        mascotaActual.setId(id);
        mascotaActual.setNombre(username);
        mascotaActual.setFoto(urlFoto);
        mascotaActual.setNum_likes(likes);

        ArrayList<Mascota> mascotas = new ArrayList<>();
        mascotas.add(mascotaActual);

        return mascotas;

    }
}
