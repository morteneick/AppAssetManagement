package com.example.asset_management.connection;

import com.example.asset_management.recycleView.Device;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetPostConnection {

    @GET("getDevices")
    Call<List<Device>> getDevices();

    @POST("getDeviceHistory")
    Call<List<Device>> getDeviceHistory(@Body Device device);

    @POST("newDevice")
    Call<String> postDevice(@Body Device device);
}
