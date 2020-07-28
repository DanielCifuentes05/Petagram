package com.daniel.petagram.pojo;

public class Media {
    private String id;
    private String caption;

    public Media() {

    }

    public Media(String id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
