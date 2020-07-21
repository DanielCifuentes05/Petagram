package com.daniel.petagram.pojo;

public class Mascota implements Comparable<Mascota>{

    private int id;
    private String nombre;
    private String raza;
    private String sexo;
    private int num_likes=0;
    private int foto;

    public Mascota() {
    }

    public Mascota(String nombre, String raza, String sexo, int num_likes, int foto) {
        this.nombre = nombre;
        this.raza = raza;
        this.sexo = sexo;
        this.num_likes = num_likes;
        this.foto = foto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getNum_likes() {
        return num_likes;
    }

    public void setNum_likes(int num_likes) {
        this.num_likes = num_likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


