package com.pnp.androidnetworking.lab3;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceGetall {
    @GET("getall.json")
    Call<com.pnp.androidnetworking.lab3.SvrResponseProducts> GetJSON();

}
