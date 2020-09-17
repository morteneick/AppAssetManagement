package com.example.asset_management.connection;

import com.example.asset_management.deviceCard.ui.reservation.Reservation;
import com.example.asset_management.recycleViewDeviceList.Device;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GetPostConnection {



    @Headers("Accept: application/json")
    @GET("/api/device/getAllDevices")
    Call<ArrayList<Device>> getDevices();

    @GET("api/borrow/getReservations")
    Call<ArrayList<Reservation>> getReservation();

    @Headers("Accept: application/json")
    @GET("api/history/getHistoryForSpecificDevice/{inventoryNumber}")
    Call<ArrayList<Device>> getDeviceOldVersion(@Path("inventoryNumber") int inventoryNumber);

    @Headers("Accept: application/json")
    @POST("api/borrow/createReservation")
    Call<ArrayList<Errors>> postNewReservation(@Body Reservation reservation);

    @Headers("Accept: application/json")
    @POST("api/device/createDevice")
    Call<ArrayList<Errors>> postDevice(@Body Device device);

    @Headers("Accept: application/json")
    @PUT("api/device/updateDevice/:{inventoryNumber}")
    Call<ArrayList<Errors>> putChangedDevice(@Path("inventoryNumber") int inventoryNumber, @Body Device device);

    @Headers("Accept: application/json")
    @HTTP(method="DELETE", path="api/borrow/cancelReservation/:{inventoryNumber}", hasBody = true)
    Call<ArrayList<Errors>> deleteReservation(@Path("inventoryNumber")int inventoryNumber, @Body Reservation reservation);

    @Headers("Accept: application/json")
    @HTTP(method="DELETE", path="api/device/deleteDevice/:{inventoryNumber}", hasBody = true)
    Call<ArrayList<Errors>> deleteDevice(@Path("inventoryNumber")int inventoryNumber);

}
