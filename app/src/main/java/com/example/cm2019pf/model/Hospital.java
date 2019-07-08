package com.example.cm2019pf.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hospital {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;
    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Phone")
    @Expose
    private Integer phone;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("District")
    @Expose
    private String district;
    @SerializedName("StandbyTimesUrl")
    @Expose
    private String standbyTimesUrl;
    @SerializedName("ShareStandbyTimes")
    @Expose
    private Boolean shareStandbyTimes;
    @SerializedName("HasCTH")
    @Expose
    private Boolean hasCTH;
    @SerializedName("HasSIGLIC")
    @Expose
    private Boolean hasSIGLIC;
    @SerializedName("HasEmergency")
    @Expose
    private Boolean hasEmergency;
    @SerializedName("InstitutionURL")
    @Expose
    private String institutionURL;
    @SerializedName("Pilot")
    @Expose
    private Boolean pilot;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStandbyTimesUrl() {
        return standbyTimesUrl;
    }

    public void setStandbyTimesUrl(String standbyTimesUrl) {
        this.standbyTimesUrl = standbyTimesUrl;
    }

    public Boolean getShareStandbyTimes() {
        return shareStandbyTimes;
    }

    public void setShareStandbyTimes(Boolean shareStandbyTimes) {
        this.shareStandbyTimes = shareStandbyTimes;
    }

    public Boolean getHasCTH() {
        return hasCTH;
    }

    public void setHasCTH(Boolean hasCTH) {
        this.hasCTH = hasCTH;
    }

    public Boolean getHasSIGLIC() {
        return hasSIGLIC;
    }

    public void setHasSIGLIC(Boolean hasSIGLIC) {
        this.hasSIGLIC = hasSIGLIC;
    }

    public Boolean getHasEmergency() {
        return hasEmergency;
    }

    public void setHasEmergency(Boolean hasEmergency) {
        this.hasEmergency = hasEmergency;
    }

    public String getInstitutionURL() {
        return institutionURL;
    }

    public void setInstitutionURL(String institutionURL) {
        this.institutionURL = institutionURL;
    }

    public Boolean getPilot() {
        return pilot;
    }

    public void setPilot(Boolean pilot) {
        this.pilot = pilot;
    }


    public Hospital(Integer id, String name, String description, Double longitude, Double latitude, String address, Integer phone, String email, String district, String standbyTimesUrl, Boolean shareStandbyTimes, Boolean hasCTH, Boolean hasSIGLIC, Boolean hasEmergency, String institutionURL, Boolean pilot) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.district = district;
        this.standbyTimesUrl = standbyTimesUrl;
        this.shareStandbyTimes = shareStandbyTimes;
        this.hasCTH = hasCTH;
        this.hasSIGLIC = hasSIGLIC;
        this.hasEmergency = hasEmergency;
        this.institutionURL = institutionURL;
        this.pilot = pilot;
    }
}
