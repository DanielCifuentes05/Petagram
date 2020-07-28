package com.daniel.petagram.pojo;

public class Mascota implements Comparable<Mascota>{

    private String id;
    private String nombre;
    //private String raza;
    //private String sexo;
    private int num_likes=0;
    private String urlFoto;

    public Mascota() {
    }

    public Mascota(String nombre, int num_likes, String urlFoto) {
        this.nombre = nombre;
        this.num_likes = num_likes;
        this.urlFoto = urlFoto;
    }




    public String getFoto() {
        return urlFoto;
    }

    public void setFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNum_likes() {
        return num_likes;
    }

    public void setNum_likes(int num_likes) {
        this.num_likes = num_likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Mascota o) {

        if (o.getNum_likes() > num_likes) {
            return -1;
        } else if (o.getNum_likes() > num_likes) {
            return 0;
        } else {
            return 1;
        }
    }
    }


