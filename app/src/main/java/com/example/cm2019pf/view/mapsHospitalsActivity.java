package com.example.cm2019pf.view;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.example.cm2019pf.MainActivity;
import com.example.cm2019pf.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapsHospitalsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SharedPreferences getlocation ;
    FusedLocationProviderClient fusedLocationProviderClient;
    SharedPreferences putlocation ;
    SharedPreferences.Editor editor;
    LocationCallback locationCallback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initViews();

        // Add a marker in Sydney and move the camera

        String longitude = getlocation.getString("Longitude","Longitude");
        String latitude = getlocation.getString("Latitude","Latitude");

        LatLng sydney = new LatLng(Double.valueOf(longitude),Double.valueOf(latitude));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void initViews (){

        getlocation = getSharedPreferences("tmplocation",Context.MODE_PRIVATE);
    }

    private void retornoPosicao() {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mapsHospitalsActivity.this);


        locationCallback = new LocationCallback() {


            @SuppressLint("CommitPrefEdits")
            @Override
            public void onLocationResult(LocationResult locationResult) {

                for (final Location location : locationResult.getLocations()) {


                    Log.i("Localizacao", " Latitude "
                            + location.getLatitude() + " Longitude " + location.getLongitude());

                    putlocation = getSharedPreferences("tmplocation", Context.MODE_PRIVATE);

                    editor = putlocation.edit();

                    String Longitude = String.valueOf(location.getLongitude());
                    String Latitude = String.valueOf(location.getLatitude());

                    editor.putString("Latitude",Latitude);
                    editor.putString("Longitude",Longitude);
                    editor.apply();
                    editor.commit();


                }

            }
        };

    }

}
