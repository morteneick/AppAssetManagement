package com.example.asset_management.connection;

import com.example.asset_management.deviceCard.ui.reservation.Reservation;
import com.example.asset_management.recycleView.Device;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetPostConnection {

    @Headers("Accept: application/json")
    @GET("/api/device/getAllDevices1")
    Call<ArrayList<Device>> getDevices();

    @GET("test")
    Call<test> get2();

    @GET("api/borrow/getReservations1")
    Call<ArrayList<Reservation>> getReservation();

    @Headers("Accept: application/json")
    @POST("api/history/getHistoryForSpecificDevice/:{inventoryNumber}")
    Call<Device> getDeviceOldVersion(@Path("inventoryNumber") int inventoryNumber);

    @Headers("Accept: application/json")
    @POST("/api/borrow/createReservation")
    Call<String> postNewReservation(@Body Reservation reservation);

    @POST("newDevice")
    Call<String> postDevice(@Body Device device);
}
