package com.daniel.petagram.restAPI.model;

public class UserResponse {

    private String id;
    private String token;
    private String id_instagram;

    public UserResponse(String id, String token, String id_instagram) {
        this.id = id;
        this.token = token;
        this.id_instagram = id_instagram;
    }

    public UserResponse() {
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
}
