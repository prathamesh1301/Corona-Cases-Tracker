package com.example.coronacasestracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoronaApiService {
    @GET("https://covid2019-api.herokuapp.com/v2/current")
    Call<StartPage> getData();

}
