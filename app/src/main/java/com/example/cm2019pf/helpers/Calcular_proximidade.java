package com.example.cm2019pf.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.cm2019pf.MainActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

public class Calcular_proximidade {

    MainActivity mainActivity;
    SharedPreferences getlocation = null;

    public Double Calcular_proximidade( Double latitudeFin, Double longitudeFin  ) {


        Double distance = null;

        getlocation = mainActivity.getSharedPreferences("tmplocation", Context.MODE_PRIVATE);

        if (getlocation != null){

        //latitude inicial do dispositivo
        LatLng posicaoInicial = new LatLng(Double.valueOf(getlocation.getString("Latitude","Latitude")),Double.valueOf(getlocation.getString("Longitude","Longitude")));
        //latitude final  do hospital
        LatLng posicaiFinal = new LatLng(latitudeFin,longitudeFin);

        distance = SphericalUtil.computeDistanceBetween(posicaoInicial, posicaiFinal);
        Log.i("LOG","A Distancia é = "+formatNumber(distance));




        }

        return distance;
    }

    private String formatNumber(double distance) {
        String unit = "m";
        if (distance > 1000) {
            distance /= 1000;
            unit = "km";
        }

        return String.format("%4.3f%s", distance, unit);
    }
}
