package com.example.cm2019pf.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.cm2019pf.model.statusHospital;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cm2019pf.R;
import com.example.cm2019pf.controller.getdataApiController;
import com.example.cm2019pf.controller.hospitalgetTimeandTypeAdapter;
import com.example.cm2019pf.helpers.IHospitalApi;
import com.example.cm2019pf.model.Api.HospitalResultStatus;

import java.util.ArrayList;
import java.util.List;

import cn.carbs.android.library.MDDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hospitalDetalheActivity extends AppCompatActivity {


    EditText nome,email,telefone,descricao,site;
    WebView webView;
    FloatingActionButton fbsendmail,fbopensite;
    String id;
    ProgressDialog dialog;


    //listview status
    List<statusHospital> hospitalResultList;
    hospitalgetTimeandTypeAdapter hospitalAdapter;
    ListView lstStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initViews();



        Intent intent = getIntent();
        if(intent != null) {


            intent.getExtras();

            String getname = intent.getStringExtra("hopitalnome");
            String getdescricao = intent.getStringExtra("hopitaldescricao");
            String getfone = intent.getStringExtra("hopitaltelefone");
            String getemail = intent.getStringExtra("hopitalemail");
            String geturlinstituicao = intent.getStringExtra("hopitalsite");

            id = intent.getStringExtra("hospitalId");

            descricao.setFocusable(false);
            telefone.setFocusable(false);
            email.setFocusable(false);
            site.setFocusable(false);
            //troca da descricao pelo nome pois tem menos texto
            descricao.setText(getname);
            telefone.setText(getfone);
            email.setText(getemail);
            site.setText(geturlinstituicao);

            this.setTitle(getdescricao);

            fbsendmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendEmail(v);
                }
            });

        }

            fbopensite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openSite(v);
                }
            });

    }

    public void initViews(){

        descricao = (EditText)findViewById(R.id.hospitalDescricao);
        email = (EditText)findViewById(R.id.hospitalEmail);
        telefone = (EditText)findViewById(R.id.hospitalTelefone);
        site = (EditText)findViewById(R.id.hospitalSite);
        fbsendmail = (FloatingActionButton)findViewById(R.id.fcsendMail);
        fbopensite = (FloatingActionButton)findViewById(R.id.fopenSite);
        lstStatus = (ListView)findViewById(R.id.lststatushospital);


        hospitalResultList = new ArrayList<statusHospital>();
        hospitalAdapter = new hospitalgetTimeandTypeAdapter(this,hospitalResultList);



    }

    public void openSite(View view) {

        webView = new WebView(this);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String url = site.getText().toString();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }



    @SuppressLint("IntentReset")
    public void sendEmail(View view) {

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");


                emailIntent.putExtra(Intent.EXTRA_EMAIL, email.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_CC, "CC");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                   // finish();
                    Log.i("Finished sending email", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(hospitalDetalheActivity.this,
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    public void openTimes(View view) {

        new MDDialog.Builder(hospitalDetalheActivity.this)
                .setTitle("Status Hospital")
                .setContentView(R.layout.hospitaltimesandtype)
                .setWidthMaxDp(300)
                .setContentItemHeightDp(400)
                .setPositiveButton("Positivo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(hospitalDetalheActivity.this, "Acao a implementar", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Negativo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(hospitalDetalheActivity.this, "Acao a implementar", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();


        get_data_from_server_urgency(id);

    }


    public void get_data_from_server_urgency(final String id) {

        dialog = new ProgressDialog(hospitalDetalheActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();

        @SuppressLint("StaticFieldLeak") AsyncTask<Void,Void,Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {



                IHospitalApi hospitalApi =  getdataApiController.getRetrofitInstance().create(IHospitalApi.class);

                if (id!=null) {

                    Call<statusHospital> call = hospitalApi.getHospitalstypebyId(id);


                    call.enqueue(new Callback<statusHospital>() {
                        @Override
                        public void onResponse(Call<statusHospital> call, Response<statusHospital> response) {
                            try{
                                statusHospital sts = call.execute().body();
                                Toast.makeText(hospitalDetalheActivity.this, " "+sts.getLastUpdate(), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }catch (Exception e){

                            }
                        }

                        @Override
                        public void onFailure(Call<statusHospital> call, Throwable t) {

                        }
                    });



                } //end if id difrente de null



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
    protected void onStart() {
        super.onStart();

        initViews();



    }

    public void openMap(View view) {
        startActivity(new Intent(this,mapsHospitalsActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
