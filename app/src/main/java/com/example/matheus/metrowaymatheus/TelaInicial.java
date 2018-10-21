package com.example.matheus.metrowaymatheus;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import com.example.matheus.metrowaymatheus.R;
import com.example.matheus.metrowaymatheus.ReadFile;
import com.example.matheus.metrowaymatheus.Estacao;


public class TelaInicial extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        ReadFile reader = new ReadFile();
        Estacao uspLeste = new Estacao("USP Leste", -23.4855, -46.5005);
        Estacao tatuape = new Estacao("Tatuapé", -23.5411, -46.5755);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(uspLeste.getPosicao(), 11));
        //------------------------------------------------------------------------------------------
        marcaEstacao(reader.readAllLine("L1Coordenadas.txt", this));
        marcaEstacao(reader.readAllLine("L2Coordenadas.txt", this));
        marcaEstacao(reader.readAllLine("L3Coordenadas.txt", this));
        marcaEstacao(reader.readAllLine("L4Coordenadas.txt", this));
        marcaEstacao(reader.readAllLine("L5Coordenadas.txt", this));
        marcaEstacao(reader.readAllLine("L7Coordenadas.txt", this));
        marcaEstacao(reader.readAllLine("L8Coordenadas(Incompleto).txt ", this));
        desenhaLinha(reader.readAll("L1Azul.txt", this), Color.BLUE);
        desenhaLinha(reader.readAll("L2Verde.txt", this), Color.GREEN);
        desenhaLinha(reader.readAll("L3Vermelha.txt", this), Color.RED);
        desenhaLinha(reader.readAll("L4Amarelha.txt", this), Color.YELLOW);
        desenhaLinha(reader.readAll("L5Lilas.txt", this), Color.parseColor("purple"));
        desenhaLinha(reader.readAll("L7Rubi.txt", this), Color.rgb(192, 1, 135));
        desenhaLinha(reader.readAll("L8Diamante.txt", this), Color.GRAY);
        desenhaLinha(reader.readAll("L9Esmeralda.txt", this), Color.rgb(1, 254, 205));
        desenhaLinha(reader.readAll("L10Turquesa.txt", this), Color.rgb(1, 101, 176));
        desenhaLinha(reader.readAll("L11Coral.txt", this), Color.rgb(255, 101, 0));
        desenhaLinha(reader.readAll("L12Safira.txt", this), Color.rgb(0, 1, 100));
    }

    public void marcaEstacao(String linha){
        String[] estacoes = linha.split("\n");
        MarkerOptions markerOptions = new MarkerOptions();

        for(String estacao : estacoes){
            String[] componentes = estacao.split(",");
            if(componentes.length > 2){
                LatLng latLng = new LatLng(Double.parseDouble(componentes[0]), Double.parseDouble(componentes[1]));
                markerOptions.position(latLng);
                map.addMarker(markerOptions);
            }
        }
    }

    public void desenhaLinha(String pos, int color){
        String[] caminhos = pos.split("],");
        String atual = "", prox = "";
        for (int i = 1; i < caminhos.length; i++){
            atual = caminhos[i-1];
            prox = caminhos[i];
            String[] atualXY = atual.split(",");
            String[] proxXY = prox.split(",");
            LatLng atualFinal = new LatLng(Double.parseDouble(atualXY[1].replace("]", "")),Double.parseDouble(atualXY[0]));
            LatLng proxFinal = new LatLng(Double.parseDouble(proxXY[1].replace("]", "")),Double.parseDouble(proxXY[0]));
            Polyline line = map.addPolyline(new PolylineOptions()
                    .add(atualFinal, proxFinal)
                    .width(14)
                    .color(color));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_inicial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
