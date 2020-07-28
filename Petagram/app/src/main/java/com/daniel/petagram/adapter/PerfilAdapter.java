package com.daniel.petagram.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daniel.petagram.R;
import com.daniel.petagram.pojo.Mascota;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public PerfilAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_fotosperfil, parent, false);
        return new PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilViewHolder holder, int position) {
        //holder.srcFotosPerfil.setImageResource(mascotas.get(position).getFoto());
        Picasso.with(activity)
                .load(mascotas.get(position).getFoto())
                .placeholder(R.drawable.mascota_mosquito_1)
                .into(holder.srcFotosPerfil);
        holder.tvLikePerfil.setText(String.valueOf(mascotas.get(position).getNum_likes()));

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private ImageView srcFotosPerfil;
        private TextView tvLikePerfil;
        private CircularImageView perfilFoto;

        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);
            srcFotosPerfil=(ImageView) itemView.findViewById(R.id.srcFotosPerfil);
            tvLikePerfil=(TextView) itemView.findViewById(R.id.tvLikePerfil);
            perfilFoto =(CircularImageView) itemView.findViewById(R.id.perfilFoto);

        }
    }

}
