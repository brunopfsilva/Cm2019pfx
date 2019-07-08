package com.example.cm2019pf.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class hospitalTimes extends RealmObject {

    @SerializedName("LastUpdate")
    @Expose
    private String LastUpdate;
    @SerializedName("Emergency")
    @Expose
    private String Emergency;
    @SerializedName("Queue")
    @Expose
    private String Queue;
    @SerializedName("Code")
    @Expose
    private Double Code;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("Time")
    @Expose
    private String Time;
    @SerializedName("Length")
    @Expose
    private String Length;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getQueue() {
        return Queue;
    }

    public void setQueue(String queue) {
        Queue = queue;
    }

    public Double getCode() {
        return Code;
    }

    public void setCode(Double code) {
        Code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
    }

    public String getEmergency() {
        return Emergency;
    }

    public void setEmergency(String emergency) {
        Emergency = emergency;
    }



    public hospitalTimes(String lastUpdate, String emergency, String queue, Double code, String description, String color, String time, String length) {
        LastUpdate = lastUpdate;
        Emergency = emergency;
        Queue = queue;
        Code = code;
        Description = description;
        this.color = color;
        Time = time;
        Length = length;
    }

    public hospitalTimes (){

    }
}
