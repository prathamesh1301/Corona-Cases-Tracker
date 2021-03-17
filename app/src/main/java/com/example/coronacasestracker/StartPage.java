package com.example.coronacasestracker;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StartPage {
    @SerializedName("data")
    private List<Data> data;
    @SerializedName("dt")
    private String dt;
    @SerializedName("ts")
    private String ts;

    public List<Data> getListData() {
        return data;
    }

    public String getDt() {
        return dt;
    }

    public String getTs() {
        return ts;
    }
}
