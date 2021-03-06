package com.daniel.petagram.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.petagram.db.ConstructorMascotas;
import com.daniel.petagram.pojo.Mascota;
import com.daniel.petagram.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    ArrayList<Mascota> mascotas ;
    Activity activity;

    public MascotaAdapter(ArrayList<Mascota> mascotas,Activity activity){
        this.mascotas=mascotas;
        this.activity=activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder holder, final int position) {
        final Mascota mascota = mascotas.get(position);
        //holder.srcFoto.setImageResource(mascota.getFoto());
        Picasso.with(activity)
                .load(mascota.getFoto())
                .placeholder(R.drawable.mascota_mosquito_1)
                .into(holder.srcFoto);
        holder.tvNombre.setText(mascota.getNombre());
        holder.tvLike.setText(String.valueOf(mascota.getNum_likes()));



        holder.btnHueso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(activity,"Has dado like a "+ mascota.getNombre() , Toast.LENGTH_SHORT).show();


                //holder.tvLike.setText(String.valueOf());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView srcFoto;
        private ImageButton btnHueso;
        private TextView tvNombre;
        private TextView tvLike;
        private LinearLayout card;
        private RelativeLayout inferiorCard;


        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            srcFoto=(ImageView) itemView.findViewById(R.id.srcFoto);
            btnHueso=(ImageButton) itemView.findViewById(R.id.btnHueso);
            tvNombre= (TextView) itemView.findViewById(R.id.tvNombre);
            tvLike= (TextView) itemView.findViewById(R.id.tvLike);
            card=(LinearLayout) itemView.findViewById(R.id.card);
            inferiorCard=(RelativeLayout) itemView.findViewById(R.id.inferiorCard);

        }
    }
}
