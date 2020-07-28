package com.daniel.petagram.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.daniel.petagram.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null,ConstantesBaseDatos.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryCrearTablaMascota = "CREATE TABLE "+ ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_RAZA + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_SEXO + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + " INTEGER " +
                ")";

        String queryCrearTablaLikes = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES + "(" +
                ConstantesBaseDatos.TABLE_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_NUMLIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
                ")";

       sqLiteDatabase.execSQL(queryCrearTablaMascota);
       sqLiteDatabase.execSQL(queryCrearTablaLikes);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Mascota> obtenerMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query= "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros= db.rawQuery(query,null);

        /*while(registros.moveToNext()){

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setRaza(registros.getString(2));
            mascotaActual.setSexo(registros.getString(3));
            mascotaActual.setFoto(registros.getInt(4));

            String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_NUMLIKES + ") as likes " +
                    " FROM " + ConstantesBaseDatos.TABLE_LIKES +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_ID_MASCOTA + "=" + mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext()){
                mascotaActual.setNum_likes(registrosLikes.getInt(0));
            }else
            {
                mascotaActual.setNum_likes(0);
            }

            mascotas.add(mascotaActual);


        }*/
        db.close();
        return mascotas;
    }



    public void insertarMascota(ContentValues contentValues){

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues );
        db.close();

    }

    public void insertarLike(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES, null, contentValues );
        db.close();
    }

    public int obtenerLikes(Mascota mascota){
        int likes= 0;
        String query="SELECT COUNT("+ ConstantesBaseDatos.TABLE_LIKES_NUMLIKES + ")" +
                    " FROM "+ ConstantesBaseDatos.TABLE_LIKES +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_ID_MASCOTA + "=" + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }
}
