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

import java.util.ArrayList;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder> {

    ArrayList<Integer> fotos;
    ArrayList<Integer> likes;
    Activity activity;

    public PerfilAdapter(ArrayList<Integer> fotos, ArrayList<Integer> likes, Activity activity){
        this.fotos=fotos;
        this.likes=likes;
        this.activity=activity;
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_fotosperfil, parent, false);
        return new PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilViewHolder holder, int position) {
        holder.srcFotosPerfil.setImageResource(fotos.get(position));
        holder.tvLikePerfil.setText(String.valueOf(likes.get(position)));
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private ImageView srcFotosPerfil;
        private TextView tvLikePerfil;

        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);
            srcFotosPerfil=(ImageView) itemView.findViewById(R.id.srcFotosPerfil);
            tvLikePerfil=(TextView) itemView.findViewById(R.id.tvLikePerfil);
        }
    }

}
