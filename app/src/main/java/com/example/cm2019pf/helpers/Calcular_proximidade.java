package com.example.cm2019pf.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

public class Calcular_proximidade {


    public Double Calcular_proximidade( Double latitudeFin, Double longitudeFin  ) {


        String distancia = null;
        Context context = null;


        assert context != null;
        SharedPreferences getlocation = context.getSharedPreferences("tmplocation", Context.MODE_PRIVATE);



        //latitude inicial do dispositivo
        LatLng posicaoInicial = new LatLng(Double.valueOf(getlocation.getString("Latitude","null")),Double.valueOf(getlocation.getString("Longitude","null")));
        //latitude final  do hospital
        LatLng posicaiFinal = new LatLng(latitudeFin,longitudeFin);

        double distance = SphericalUtil.computeDistanceBetween(posicaoInicial, posicaiFinal);
        Log.i("LOG","A Distancia é = "+formatNumber(distance));

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
