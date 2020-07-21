package com.daniel.petagram.db;

import android.content.ContentValues;
import android.content.Context;

import com.daniel.petagram.R;
import com.daniel.petagram.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){

        BaseDatos db = new BaseDatos(context);
        insetarMascotas(db);
        return db.obtenerMascotas();
    }


    public void insetarMascotas(BaseDatos db){

        ArrayList<Mascota> mascotas = llenarArrayMascotas();

        for(int i = 0; i < mascotas.size(); i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, mascotas.get(i).getNombre());
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_RAZA, mascotas.get(i).getRaza());
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_SEXO, mascotas.get(i).getSexo());
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, mascotas.get(i).getFoto());
            db.insertarMascota(contentValues);

        }


    }

    public ArrayList<Mascota> llenarArrayMascotas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();

        mascotas.add(new Mascota("Lupe","Gato","Hembra",3, R.drawable.gato3));
        mascotas.add(new Mascota("Max","Perro", "Macho",5, R.drawable.mascota_mosquito_1));
        mascotas.add(new Mascota("Rufo","Perro", "Macho",7, R.drawable.perrito1));
        mascotas.add(new Mascota("Hana","Perro", "Hembra",6, R.drawable.perro));
        mascotas.add(new Mascota("Fiona","Perro", "Hembra",9, R.drawable.perro6));
        mascotas.add(new Mascota("Aron","Gato", "Macho",6, R.drawable.gato));
        return mascotas;
    }

    public void darLikeMascota(Mascota mascota){

        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_NUMLIKES, LIKE);
        db.insertarLike(contentValues);

    }

    public int obtenerLikesMascota(Mascota mascota){

        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikes(mascota);

    }


}
