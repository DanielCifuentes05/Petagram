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

import retrofit2.http.GET;

public class MediaDeserializador implements JsonDeserializer<MediaResponse> {
    @Override
    public MediaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        MediaResponse mediaResponse = gson.fromJson(json, MediaResponse.class);
        JsonArray mediaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE);

        mediaResponse.setMedia(deserializarMedia(mediaResponseData));
        return mediaResponse;
    }

    public ArrayList<Media> deserializarMedia(JsonArray mediaResponseData){

        ArrayList<Media> media = new ArrayList<>();

        for (int i = 0; i < mediaResponseData.size() ; i++  ){

            JsonObject mediaResponseDataObject =  mediaResponseData.get(i).getAsJsonObject();

            String id = mediaResponseDataObject.get(JsonKeys.ID).getAsString();
            String caption = "hola"; //mediaResponseDataObject.get(JsonKeys.CAPTION).getAsString();

            Media mediaActual = new Media();
            mediaActual.setId(id);
            mediaActual.setCaption(caption);

            media.add(mediaActual);

        }

        return media;

    }
}
