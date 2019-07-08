package com.example.cm2019pf;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cm2019pf.controller.HospitalAdapter;
import com.example.cm2019pf.controller.getdataApiController;
import com.example.cm2019pf.helpers.IHospitalApi;
import com.example.cm2019pf.model.Hospital;
import com.example.cm2019pf.model.Api.HospitalResult;
import com.example.cm2019pf.view.mapsHospitalsActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener   {


    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private HospitalAdapter hospitalAdapter;
    private List<Hospital> hospitalResultList;
    private ProgressDialog dialog;
    //chamada google api cliente
    private GoogleApiClient mGoogleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);



        //realm configuration
        Realm.init(getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        //location api configuration




        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, mapsHospitalsActivity.class);
                startActivity(it);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);







            //Carrega as views da MainActivity




    }

    @Override
    protected void onStart() {
        super.onStart();
        initiViews();
        get_data_from_server();
    }

    private void initiViews(){


        recyclerView = (RecyclerView)findViewById(R.id.rcsgetHospital);
        //lista onde vao ser adicionados os objectos
        hospitalResultList = new ArrayList<Hospital>();




        //seta o layout do recyclerview
        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        hospitalAdapter = new HospitalAdapter(this,hospitalResultList);
        recyclerView.setAdapter(hospitalAdapter);


         }


    public void get_data_from_server() {


        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();

        @SuppressLint("StaticFieldLeak") AsyncTask<Void,Void,Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {



                IHospitalApi hospitalApi = getdataApiController.getRetrofitInstance().create(IHospitalApi.class);
                Call<HospitalResult> requesthospitals =  hospitalApi.listHospitais();

                requesthospitals.enqueue(new Callback<HospitalResult>() {
                    @Override
                    public void onResponse(Call<HospitalResult> call, Response<HospitalResult> response) {
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
                        if(!response.isSuccessful()){
                            Log.e("erro",""+response.code());
                        }else{
                            //pega a lista de hospital vindo da url
                            HospitalResult hospitals = response.body();
                            try{
                                for(Hospital h: hospitals.getResult()){
                                    Log.i("Hospital "+" Nome "+h.getName(),"Distro "+h.getDistrict());



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
                                    hospitalResultList.add(hospitaldatamodel);
                                    hospitalAdapter.notifyDataSetChanged();
                                }



                            }catch (Exception ex){

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HospitalResult> call, Throwable t) {
                        if (dialog.isShowing()){
                            dialog.dismiss();
                            Toast.makeText(MainActivity.this, "Error ao carregar os dados", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                hospitalAdapter.notifyDataSetChanged();
            }
        };

        task.execute();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
