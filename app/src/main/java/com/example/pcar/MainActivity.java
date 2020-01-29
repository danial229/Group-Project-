package com.example.pcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    ImageButton imgbtnExModel, imgbtnCamera, imgbtnGallery;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        drawerLayout = findViewById(R.id.drawer_layout);


        imgbtnExModel = findViewById(R.id.imgbtnExM);
        imgbtnExModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Existing_Model.class);
                startActivity(intent);

            }
        });

        imgbtnCamera = findViewById(R.id.imgbtnCamera);
        imgbtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Camera.class);
                startActivity(intent1);
            }
        });

        imgbtnGallery = findViewById(R.id.imgbtnGallery);
        imgbtnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, Gallery.class);
                startActivity(intent2);
            }
        });



        navigationView = findViewById(R.id.nav_view);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }


    //Closes the Navigation Drawer by pressing the back button
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);

        }else {
            super.onBackPressed();
        }
    }


    //Items in the Navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.camera:
                Toast.makeText(MainActivity.this, "Camera Selected", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this, Camera.class);
                startActivity(intent1);
                break;
            case R.id.ExModel:
                Toast.makeText(MainActivity.this, "Existing Model Selected", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, Existing_Model.class);
                startActivity(intent2);
                break;
            case R.id.garage:
                Toast.makeText(MainActivity.this, "Garage Selected", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps"));
                startActivity(intent3);
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
