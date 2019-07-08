package com.example.cm2019pf.model.Api;

import com.example.cm2019pf.model.Hospital;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HospitalResult {

    @SerializedName("Result")
    @Expose
    private List<Hospital> result;

    public List<Hospital> getResult() {
        return result;
    }

    public void setResult(List<Hospital> result) {
        this.result = result;
    }
}
