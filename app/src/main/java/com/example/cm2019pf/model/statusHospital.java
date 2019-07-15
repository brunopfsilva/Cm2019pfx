package com.example.cm2019pf.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class statusHospital {



    @SerializedName("ScaleType")
    @Expose
    private String ScaleType;
    @SerializedName("LastUpdate")
    @Expose
    private String LastUpdate;


    public String getScaleType() {
        return ScaleType;
    }

    public void setScaleType(String scaleType) {
        ScaleType = scaleType;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }

    public statusHospital(String scaleType, String lastUpdate) {
        ScaleType = scaleType;
        LastUpdate = lastUpdate;
    }
}
