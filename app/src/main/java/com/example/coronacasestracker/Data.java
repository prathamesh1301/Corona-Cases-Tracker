package com.example.coronacasestracker;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("location")
    private String location;
    @SerializedName("confirmed")
    private Long confirmed;
    @SerializedName("deaths")
    private Long death;
    @SerializedName("recovered")
    private Long recovered;
    @SerializedName("active")
    private Long active;

    public String getLocation() {
        return location;
    }

    public Long getConfirmed() {
        return confirmed;
    }

    public Long getDeath() {
        return death;
    }

    public Long getRecovered() {
        return recovered;
    }

    public Long getActive() {
        return active;
    }
}
