package com.example.cm2019pf.view;

import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.cm2019pf.MainActivity;
import com.example.cm2019pf.R;
import com.example.cm2019pf.controller.getdataApiController;
import com.example.cm2019pf.helpers.IHospitalApi;
import com.example.cm2019pf.model.Api.HospitalResult;
import com.example.cm2019pf.model.Hospital;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mapsHospitalsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FusedLocationProviderClient fusedLocationProviderClient;
    SharedPreferences putlocation;
    SharedPreferences.Editor editor;
    LocationCallback locationCallback;
    Marker markerhospital;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        get_data_from_server();

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
        SharedPreferences getlocation = getSharedPreferences("tmplocation", Context.MODE_PRIVATE);


        String longitude = getlocation.getString("Longitude", "Longitude");
        String latitude = getlocation.getString("Latitude", "Latitude");


        LatLng sydney = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(21.0f));

    }

    private void initViews() {


        //evento modificar
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                if (markerhospital != null) {
                    //renova marcadores
                    markerhospital.remove();
                }

                //customAdapter(new LatLng());
            }
        });

    }

    public void customAdapter(LatLng latLng, String title, String snippet) {

        MarkerOptions options = new MarkerOptions();
        options.position(latLng).title(title).snippet(snippet).draggable(true);


        markerhospital = mMap.addMarker(options);
    }


    public void get_data_from_server() {


        dialog = new ProgressDialog(mapsHospitalsActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {


                IHospitalApi hospitalApi = getdataApiController.getRetrofitInstance().create(IHospitalApi.class);
                Call<HospitalResult> requesthospitals = hospitalApi.listHospitais();

                requesthospitals.enqueue(new Callback<HospitalResult>() {
                    @Override
                    public void onResponse(Call<HospitalResult> call, Response<HospitalResult> response) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        if (!response.isSuccessful()) {
                            Log.e("erro", "" + response.code());
                        } else {
                            //pega a lista de hospital vindo da url
                            HospitalResult hospitals = response.body();
                            try {
                                for (Hospital h : hospitals.getResult()) {
                                    Log.i("Hospital " + " Nome " + h.getName(), "Distro " + h.getDistrict());


                                    Hospital hospitaldatamodel = new Hospital(

                                            h.getId(),
                                            h.getName(),
                                            h.getDescription(),
                                            h.getLongitude(),
                                            h.getLatitude(),
                                            h.getAddress(),
                                            h.getPhone(),
                                            h.getEmail(),
                                            h.getDistrict(),
                                            h.getStandbyTimesUrl(),
                                            h.getShareStandbyTimes(),
                                            h.getHasCTH(),
                                            h.getHasSIGLIC(),
                                            h.getHasEmergency(),
                                            h.getInstitutionURL(),
                                            h.getPilot()

                                    );
                                    //adiciona hospitais no array

                                    customAdapter(new LatLng(h.getLatitude(), h.getLongitude()), h.getName(), h.getDescription());

                                }


                            } catch (Exception ex) {

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HospitalResult> call, Throwable t) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                            Toast.makeText(mapsHospitalsActivity.this, "Error ao carregar os dados", Toast.LENGTH_SHORT).show();

                        }

                    }
                });


                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


            }
        };

        task.execute();

    }

}