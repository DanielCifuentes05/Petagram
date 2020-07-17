package com.daniel.petagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Toolbar mytoolbar;
    private TabLayout mytab;
    private ViewPager myviewpager;


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

        }
        return super.onOptionsItemSelected(item);
    }
}