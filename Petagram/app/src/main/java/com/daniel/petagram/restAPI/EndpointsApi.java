package com.daniel.petagram.restAPI;

import com.daniel.petagram.restAPI.model.MascotaResponse;

import com.daniel.petagram.restAPI.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface EndpointsApi {

    @GET(ConstantesRestApi.GET_MEDIA)
    Call<MascotaResponse> getMediaId();


    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UserResponse> registrarTokenId(@Field("id_instagram") String id_instagram,
                                        @Field("token") String token);

}
