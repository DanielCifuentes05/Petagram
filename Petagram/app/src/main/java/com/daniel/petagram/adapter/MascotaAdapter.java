package com.daniel.petagram.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
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

import com.daniel.petagram.MainActivity;
import com.daniel.petagram.db.ConstructorMascotas;
import com.daniel.petagram.pojo.Mascota;
import com.daniel.petagram.R;
import com.daniel.petagram.restAPI.EndpointsApi;
import com.daniel.petagram.restAPI.adapter.RestApiAdapter;
import com.daniel.petagram.restAPI.model.LikeResponse;
import com.daniel.petagram.restAPI.model.UserResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    ArrayList<Mascota> mascotas ;
    Activity activity;
    private static final String TAG = "Fragment Mascota";
    private String token;

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

                recibirLikes(position);

                //holder.tvLike.setText(String.valueOf());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public void recibirLikes(final int position){

        Mascota mascota = mascotas.get(position);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        token = task.getResult().getToken();

                        // Log and toast
                        String msg = "TOKEN: " + token;
                        Log.d(TAG, msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionServer();
        final Call<LikeResponse> usuarioResponseCall = endpointsApi.registrarLikeId(mascota.getNombre(), token, mascota.getId());
        usuarioResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                LikeResponse likeResponse = response.body();
                if (response.body() == null) {
                    Log.e("Fallo peticion RESPONSE", response.errorBody().toString());
                    recibirLikes(position);

                } else {

                    Log.d("ID_FIREBASE", likeResponse.getId());
                    Log.d("ID_TOKEN", likeResponse.getToken());
                    Log.d("ID_INSTAGRAM", likeResponse.getId_instagram());
                    Log.d("ID_FOTO", likeResponse.getId_foto());

                }
            }
            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {
                Log.e("Fallo peticion SERVER", t.toString()); }
        });
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
