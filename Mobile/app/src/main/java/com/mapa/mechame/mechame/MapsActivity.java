package com.mapa.mechame.mechame;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            public void onMapClick(LatLng latLng){

                customAddMarker(new LatLng(latLng.latitude,latLng.longitude),"Novo Marcador","Novo  Local");
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            public boolean onMarkerClick(Marker marker){
                LatLng latLng= marker.getPosition();
                customAddMarker(new LatLng(latLng.latitude,latLng.longitude),"Marcador","Novo Local");


            return false;
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();


    }


    // Metodo para mostrar o mapa na tela !!!
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {

        Localizador localizador = new Localizador(this);
        LatLng local = localizador.getCoordenada("Rua manoel barbalho de lima");
        centralizaNo(local);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng));

    }

    public void centralizaNo(LatLng local){
        GoogleMap mapa = mMap;
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 17));
        mMap.addMarker(new MarkerOptions().position(local).title("Você está Aqui !!").snippet(local.toString()));

    }


    public void customAddMarker(LatLng lating, String title, String snippet){
        MarkerOptions option = new MarkerOptions();
        option.position(lating).title(title).snippet(snippet).draggable(true);
        marker = mMap.addMarker(option);

    }



}
