package com.example.cm2019pf.model.Api;

import com.example.cm2019pf.model.statusHospital;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class HospitalResultStatus {


    @SerializedName("Result")
    @Expose
    private List<statusHospital> result;

    public List<statusHospital> getResult() {
        return result;
    }
    //duvida aqui

    public void setResult(List<statusHospital> result) {
        this.result = result;
    }
}
