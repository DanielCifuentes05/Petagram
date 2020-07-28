package com.daniel.petagram.restAPI.model;

import com.daniel.petagram.pojo.Media;

import java.util.ArrayList;

public class MediaResponse {

    ArrayList<Media> media;

    public ArrayList<Media> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<Media> media) {
        this.media = media;
    }
}
