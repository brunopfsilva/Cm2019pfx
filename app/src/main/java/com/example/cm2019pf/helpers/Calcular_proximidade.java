package com.example.cm2019pf.helpers;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

public class Calcular_proximidade {


    public String Calcular_proximidade(Double latitudeIni, Double longitudeIni ,Double latitudeFin, Double longitudeFin  ) {


        String distancia = null;

        LatLng posicaoInicial = new LatLng(latitudeIni,longitudeIni);
        LatLng posicaiFinal = new LatLng(latitudeFin,longitudeFin);

        double distance = SphericalUtil.computeDistanceBetween(posicaoInicial, posicaiFinal);
        Log.i("LOG","A Distancia é = "+formatNumber(distance));

        return distancia;

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
