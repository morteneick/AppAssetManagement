package com.example.asset_management.connection;

import com.example.asset_management.deviceCard.ui.reservation.Reservation;
import com.example.asset_management.login.Login;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.recycleViewDeviceList.Device;

import java.util.ArrayList;

import okhttp3.ResponseBody;
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
    @GET("/api/login")
    Call<ArrayList<UserInfo>> getLogin();

    @Headers("Accept: application/json")
    @POST("/api/login")
    Call<ResponseBody> postLogin(@Body Login login);

    @Headers("Accept: application/json")
    @GET("/api/device/getAllDevices")
    Call<ArrayList<Device>> getDevices();

    @GET("api/borrow/getReservations")
    Call<ArrayList<Reservation>> getReservation();

    @Headers("Accept: application/json")
    @GET("api/history/getHistoryForSpecificDevice/{inventoryNumber}")
    Call<ArrayList<Device>> getDeviceOldVersion(@Path("inventoryNumber") int inventoryNumber);

    @Headers("Accept: application/json")
    @GET("api/user/getSpecificUser/:{workerId}")
    Call<UserInfo> getUser(@Path("workerId") int workerId);

    @Headers("Accept: application/json")
    @GET("api/user/getAllUsers")
    Call<ArrayList<UserInfo>> getAllUsers();

    @Headers("Accept: application/json")
    @POST("api/borrow/createReservation")
    Call<ArrayList<Errors>> postNewReservation(@Body Reservation reservation);

    @Headers("Accept: application/json")
    @POST("api/device/createDevice")
    Call<ArrayList<Errors>> postDevice(@Body Device device);

    @Headers("Accept: application/json")
    @POST("api/device/createDevice")
    Call<ArrayList<Errors>> postUser(@Body UserInfo userInfo);

    @Headers("Accept: application/json")
    @PUT("api/user/updateUser/{workerId}")
    Call<ArrayList<Errors>> putUpdateUser(@Path("workerId") int workerId, @Body UserInfo userInfo);

    @Headers("Accept: application/json")
    @PUT("api/device/updateDevice/{inventoryNumber}")
    Call<ArrayList<Errors>> putChangedDevice(@Path("inventoryNumber") int inventoryNumber, @Body Device device);

    @Headers("Accept: application/json")
    @HTTP(method="DELETE", path="api/user/deleteUser/{workerId}", hasBody = true)
    Call<ArrayList<Errors>> deleteUser(@Path("workerId")int workerId, @Body UserInfo userInfo);

    @Headers("Accept: application/json")
    @HTTP(method="DELETE", path="api/borrow/cancelReservation/{inventoryNumber}", hasBody = true)
    Call<ArrayList<Errors>> deleteReservation(@Path("inventoryNumber")int inventoryNumber, @Body Reservation reservation);

    @Headers("Accept: application/json")
    @HTTP(method="DELETE", path="api/device/deleteDevice/{inventoryNumber}", hasBody = true)
    Call<ArrayList<Errors>> deleteDevice(@Path("inventoryNumber")int inventoryNumber);

    @Headers("Accept: application/json")
    @GET("/api/notification/tuv")
    Call<ArrayList<Device>> getTuev();

    @Headers("Accept: application/json")
    @GET("/api/notification/uvv")
    Call<ArrayList<Device>> getUvv();

    @Headers("Accept: application/json")
    @GET("/api/notification/maintenance")
    Call<ArrayList<Device>> getMaintenance();

    @Headers("Accept: application/json")
    @GET("/api/notification/booking/{workerId}")
    Call<ArrayList<Device>> getBooking(@Path("workerId")int workerId);

}
