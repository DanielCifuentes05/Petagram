package com.daniel.petagram.restAPI.model;

public class LikeResponse {

    private String id;
    private String token;
    private String id_instagram;
    private String id_foto;

    public LikeResponse(String id, String token, String id_instagram, String id_foto) {
        this.id = id;
        this.token = token;
        this.id_instagram = id_instagram;
        this.id_foto = id_foto;
    }

    public LikeResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId_instagram() {
        return id_instagram;
    }

    public void setId_instagram(String id_instagram) {
        this.id_instagram = id_instagram;
    }

    public String getId_foto() {
        return id_foto;
    }

    public void setId_foto(String id_foto) {
        this.id_foto = id_foto;
    }
}
