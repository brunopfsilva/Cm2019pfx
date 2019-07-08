package com.example.cm2019pf.helpers;

import com.example.cm2019pf.model.Api.HospitalResult;
import com.example.cm2019pf.model.Api.HospitalResultStatus;
import com.example.cm2019pf.model.statusHospital;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IHospitalApi {


    @GET("/standbyTime/{id}")
    Call<statusHospital> getHospitalstypebyId(@Path("id")String id);


    @GET("institution")
    Call<HospitalResult> listHospitais();




}
