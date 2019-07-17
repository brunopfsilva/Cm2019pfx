package com.example.cm2019pf.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.cm2019pf.MainActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import static java.lang.Double.valueOf;

public class Calcular_proximidade {


    public Double Calcular_proximidade(Context context, Double latitudeFin, Double longitudeFin  ) {


        Double distance = null;

        SharedPreferences getlocation = context.getSharedPreferences("tmplocation", Context.MODE_PRIVATE);

        if (getlocation != null){

        //latitude inicial do dispositivo

        LatLng posicaoInicial = new LatLng(Double.valueOf((getlocation.getString("Latitude","Latitude"))),Double.valueOf(getlocation.getString("Longitude","Latitude")));
        //LatLng posicaoInicial = new LatLng(latitudeIni,LongitudeIni);
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
