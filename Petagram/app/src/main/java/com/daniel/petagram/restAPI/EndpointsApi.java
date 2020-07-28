package com.daniel.petagram.restAPI;

import com.daniel.petagram.restAPI.model.MascotaResponse;
import com.daniel.petagram.restAPI.model.MediaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface EndpointsApi {


    @GET
    Call<MascotaResponse> getRecentMedia(@Url String url);

    @GET(ConstantesRestApi.GET_MEDIA)
    Call<MediaResponse> getMediaId();

}
