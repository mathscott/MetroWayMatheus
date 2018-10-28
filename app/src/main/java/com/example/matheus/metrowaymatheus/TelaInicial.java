package com.example.matheus.metrowaymatheus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.content.pm.PackageManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.content.ContextCompat;
import android.Manifest;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;

import java.io.IOException;
import java.util.List;

//testedev
public class TelaInicial extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, OnMyLocationButtonClickListener, OnMyLocationClickListener {

    private GoogleMap map;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private Context context;
    static String estacoes [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.baseline_add_location_black_18dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSecondActivity(view);
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

        criaVetorEstacoes();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {





                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, TelaSelecao.class);
        startActivity(intent);


    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
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
        System.out.println("teste");
        map = googleMap;
        ReadFile reader = new ReadFile();
        Estacao uspLeste = new Estacao("USP Leste", -23.4855, -46.5005);
        Estacao tatuape = new Estacao("Tatuapé", -23.5411, -46.5755);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(uspLeste.getPosicao(), 11));
        map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this, R.raw.mapstyle));
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



        if (ContextCompat.checkSelfPermission(TelaInicial.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(TelaInicial.this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(TelaInicial.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            map.setMyLocationEnabled(true);
            map.setOnMyLocationButtonClickListener(this);
            map.setOnMyLocationClickListener(this);
        }


    }

    public void criaVetorEstacoes () {
        ReadFile reader = new ReadFile();
        String linha = "";
        linha = (reader.readAllLine("L1CoordenadasNome.txt", this));
        linha = linha +(reader.readAllLine("L2CoordenadasNome.txt", this));
        linha = linha +(reader.readAllLine("L3CoordenadasNome.txt", this));
        linha = linha +(reader.readAllLine("L4CoordenadasNome.txt", this));
        linha = linha +(reader.readAllLine("L5CoordenadasNome.txt", this));
        linha = linha +(reader.readAllLine("L7CoordenadasNome.txt", this));
        linha = linha +(reader.readAllLine("L8Coordenadas(Incompleto)Nome.txt ", this));

        estacoes = linha.split("\n");

        estacoes[0] = "Selecione uma estação";


////        estacoes = componentes.split("\n");
//        Log.d("estacoes", Arrays.toString(estacoes));


    }

    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            map.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    public void marcaEstacao(String linha){
//        ImageView imagem = (ImageView) findViewById(R.id.pic);
//        String estacao1 = "linhaazul";
//        int imageResource = getResources().getIdentifier("@drawable/"+estacao1, null, this.getPackageName());
//        imagem.setImageResource(imageResource);
        InfoWindowData info = new InfoWindowData();
        String[] estacoes = linha.split("\n");
        MarkerOptions markerOptions = new MarkerOptions();

        for(String estacao : estacoes){
            String[] componentes = estacao.split(",");
            Log.i("estacao", estacao);
            if(componentes.length > 2){
                LatLng latLng = new LatLng(Double.parseDouble(componentes[0]), Double.parseDouble(componentes[1]));
                markerOptions.position(latLng);
                markerOptions.title(componentes[2]);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.baseline_train_black_18dp));
//                map.addMarker(markerOptions);

                //info.setImage("linhaazul");

                CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
                map.setInfoWindowAdapter(customInfoWindow);

                Marker m = map.addMarker(markerOptions);

                m.setTag(info);
                m.showInfoWindow();
            }
        }
    }

    public void desenhaLinha(String pos, int color){
        String[] caminhos = pos.split("],");
        String atual = "", prox = "";
        PolylineOptions options = new PolylineOptions().width(14).color(color).geodesic(true);
        for (int i = 0; i < caminhos.length; i++){
            atual = caminhos[i];
            //prox = caminhos[i];
            String[] atualXY = atual.split(",");
            //String[] proxXY = prox.split(",");
            LatLng atualFinal = new LatLng(Double.parseDouble(atualXY[1].replace("]", "")),Double.parseDouble(atualXY[0]));
            //LatLng proxFinal = new LatLng(Double.parseDouble(proxXY[1].replace("]", "")),Double.parseDouble(proxXY[0]));
            options.add(atualFinal);
        }
        map.addPolyline(options);
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
