package com.mapa.mechame.mechame;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Rafael on 17/04/2015.
 */
public class Localizador  {

    private Geocoder geocoder;
    public Localizador(Context ctx){
        geocoder = new Geocoder(ctx);
    }
    public LatLng getCoordenada(String endereco){
        try {

            List<Address> listaEnd = geocoder.getFromLocationName(endereco, 1);
            if(! listaEnd.isEmpty()){
                Address address = listaEnd.get(0);

                return new LatLng(address.getLatitude(),address.getLongitude());
            }
            return null;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;

    }
}
