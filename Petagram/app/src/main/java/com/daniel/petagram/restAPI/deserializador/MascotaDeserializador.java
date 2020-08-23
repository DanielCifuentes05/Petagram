package com.daniel.petagram.restAPI.deserializador;

import com.daniel.petagram.pojo.Mascota;
import com.daniel.petagram.restAPI.JsonKeys;
import com.daniel.petagram.restAPI.model.MascotaResponse;
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
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE);

        mascotaResponse.setMascotas(deserializarMascota(mascotaResponseData));
        return mascotaResponse;
    }

    public ArrayList<Mascota> deserializarMascota(JsonArray mascotaResponseData){

        ArrayList<Mascota> mascotas = new ArrayList<>();

        for (int i = 0; i < mascotaResponseData.size(); i++) {

            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            Random rand = new Random();

            String id = mascotaResponseDataObject.get(JsonKeys.ID).getAsString();
            String urlFoto = mascotaResponseDataObject.get(JsonKeys.MEDIA_URL).getAsString();
            String username = mascotaResponseDataObject.get(JsonKeys.USER_NAME).getAsString();
            int likes = rand.nextInt(25);

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setNombre(username);
            mascotaActual.setFoto(urlFoto);
            mascotaActual.setNum_likes(likes);
            mascotas.add(mascotaActual);
            
        }


        return mascotas;

    }
}
