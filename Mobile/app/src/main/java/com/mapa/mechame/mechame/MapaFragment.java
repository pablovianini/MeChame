package com.mapa.mechame.mechame;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rafael on 17/04/2015.
 */
public class MapaFragment extends SupportMapFragment {

    @Override
    public void onResume() {
        super.onResume();

        Localizador localizador = new Localizador(getActivity());
        LatLng local = localizador.getCoordenada("Rua manoel barbalho de lima");

        centralizaNo(local);
    }

    public void centralizaNo(LatLng local){
        GoogleMap mapa = getMap();
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(local,17));


    }
}
