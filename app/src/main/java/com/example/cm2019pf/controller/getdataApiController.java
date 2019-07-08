package com.example.cm2019pf.controller;

import com.example.cm2019pf.helpers.Common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class getdataApiController {


    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(Common.URL_API_LISTA_INSTITUICAO_TEMPO_ESPERA)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }





}
