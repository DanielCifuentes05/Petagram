package com.daniel.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daniel.petagram.adapter.MascotaAdapter;
import com.daniel.petagram.adapter.PageAdapter;
import com.daniel.petagram.fragment.PerfilFragment;
import com.daniel.petagram.fragment.RecyclerViewFragment;
import com.daniel.petagram.pojo.Mascota;
import com.daniel.petagram.restAPI.EndpointsApi;
import com.daniel.petagram.restAPI.adapter.RestApiAdapter;
import com.daniel.petagram.restAPI.model.UserResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private Toolbar mytoolbar;
    private TabLayout mytab;
    private ViewPager myviewpager;
    private String token;
    private String id_instagram;
    private static final String TAG = "Activity Main";
    private int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytoolbar=(Toolbar) findViewById(R.id.miToolbar);
        mytab = (TabLayout) findViewById(R.id.myTab);
        myviewpager = (ViewPager) findViewById(R.id.myViewPager);

        if(mytoolbar!=null){
            setSupportActionBar(mytoolbar);
        }


        ImageButton cinco = (ImageButton) findViewById(R.id.cincoMejores);

        /**/
        setUpViewPager();
        myFab();

        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(MainActivity.this, Mejores5.class);
                startActivity(intent);
            }
        });

        Bundle params = getIntent().getExtras();


        if (params != null) {
            id_instagram = params.getString(getResources().getString(R.string.clvCuenta));
        } else {
            id_instagram = "cloy_perrito";
        }
        Log.d(TAG, "CUENTA INSTAGRAM ON"+ id_instagram );

    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments =new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    private void setUpViewPager(){
        myviewpager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        mytab.setupWithViewPager(myviewpager);

        mytab.getTabAt(0).setIcon(R.drawable.ic_home);
        mytab.getTabAt(1).setIcon(R.drawable.ic_perfil);


    }



    public void myFab(){
        FloatingActionButton Fab = (FloatingActionButton) findViewById(R.id.myFAB);
        Fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), getResources().getString(R.string.toast), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.mContacto:
                Intent intent=new Intent(MainActivity.this , Formulario.class);
                startActivity(intent);
                break;

            case R.id.mAcerca:
                Intent intent1=new Intent(MainActivity.this , AcercaDe.class);
                startActivity(intent1);
                break;

            case R.id.mConfigurar:
                Intent intent2=new Intent(MainActivity.this , ConfigurarCuenta.class);
                startActivity(intent2);
                finish();
                break;

            case R.id.mNotificacion:
                //Toast.makeText(MainActivity.this, id_instagram, Toast.LENGTH_SHORT).show();
                recibirNotificaciones();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(getString(R.string.clvCuenta), id_instagram);
        Log.d(TAG, "CUENTA INSTAGRAM SAVE"+ id_instagram );
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        id_instagram = savedInstanceState.getString(getString(R.string.clvCuenta));
        Log.d(TAG, "CUENTA INSTAGRAM RESTORE "+ id_instagram );

    }

    public void recibirNotificaciones(){
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
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionServer();
        final Call<UserResponse> usuarioResponseCall = endpointsApi.registrarTokenId(id_instagram, token);
        usuarioResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (response.body() == null) {
                    Log.e("Fallo peticion RESPONSE", response.errorBody().toString());
                    recibirNotificaciones();

                } else {
                    Toast.makeText(MainActivity.this, id_instagram + " recibira notificaciones", Toast.LENGTH_SHORT).show();
                    Log.d("ID_FIREBASE", userResponse.getId());
                    Log.d("ID_TOKEN", userResponse.getToken());
                    Log.d("ID_INSTAGRAM", userResponse.getId_instagram());

                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("Fallo peticion SERVER", t.toString()); }
        });
    }
}