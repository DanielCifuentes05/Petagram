package com.daniel.petagram.restAPI;

public final class ConstantesRestApi {

    public static final String ROOT_URL = "https://graph.instagram.com/";
    public static final String ACCESS_TOKEN = "&access_token=IGQVJYcFc4M1ZANal91Q0s1NUdpNzhjd0N1WFFZARHFZASUFNVG1qeHQ2NmlXekNiMjl6cWJaaHVQWm5VWWRqSmItbkkzeU5NX0tzX3VoMjFoRDdyRkpqX2dHeFdCLXgySnVoUG1SMkx3";
    public static final String ME = "me";
    public static final String MEDIA= "/media";
    public static final String FIELDS = "?fields=";
    public static final String USER_FIELDS="id,username";
    public static final String MEDIA_FIELDS="id,username,media_url,caption";
    public static final String MEDIA_DATA_FIELDS="id,media_type,media_url,username,timestamp";
    public static final String GET_USER= ME + FIELDS + USER_FIELDS + ACCESS_TOKEN;
    public static final String GET_MEDIA= ME + MEDIA + FIELDS + MEDIA_FIELDS + ACCESS_TOKEN;
    public static final String GET_MEDIA_DATA= FIELDS + MEDIA_DATA_FIELDS + ACCESS_TOKEN;

    public static final String ROOT_URL_SERVER = "https://stark-escarpment-07529.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "token-device/";
    public static final String KEY_POST_ID_LIKE = "get-likes/";


}
