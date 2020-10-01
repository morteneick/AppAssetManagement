package com.example.asset_management.connection;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asset_management.deviceCard.ui.reservation.Reservation;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.login.Login;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.recycleViewDeviceList.Device;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Connection
 * <p>
 *     Version 1.0
 * </p>
 * 30.08.2020
 * AUTHOR: Dominik Dziersan
 */
public class Connection {

    private String baseURL = "http://10.0.2.2:3000/";
    private String msgNoConnectionServer = "Keine Verbindung zum Server.";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getLoginData(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<UserInfo>> call = getPostConnection.getLogin();

        call.enqueue(new Callback<ArrayList<UserInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<UserInfo>> call,
                                   retrofit2.Response<ArrayList<UserInfo>> response) {
                
                if (!response.isSuccessful()) {
                    Toast.makeText(context,"RESPONSE UNSUCCESSFUL",Toast.LENGTH_SHORT).show();
                    return;
                }
               // Toast.makeText(context,ArrayList<UserInfo>,Toast.LENGTH_SHORT).show();

                ArrayList<UserInfo> posts = response.body();

                JsonHandler.createJsonFromLogin(posts, "Login.json", context);
            }
            @Override
            public void onFailure(Call<ArrayList<UserInfo>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void postLogin(Login login, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ResponseBody> call = getPostConnection.postLogin(login);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   retrofit2.Response<ResponseBody> response) {

                if (!response.isSuccessful()){
                    String s = response.toString();
                    Toast.makeText(context,"Error" + s,Toast.LENGTH_SHORT).show();
/*
                    String error = "nix";
                    try {
                        error = response.errorBody().string();
                        error = error.replace("\"","");
                        Toast.makeText(context,error,Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                }
                String s = response.body().toString();
                Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onFailureMessage(context, t);

            }
        });

    }

    public void getDeviceList(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Device>> call = getPostConnection.getDevices();

        call.enqueue(new Callback<ArrayList<Device>>() {
            @Override
            public void onResponse(Call<ArrayList<Device>> call,
                                   retrofit2.Response<ArrayList<Device>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                Calendar calendar = Calendar.getInstance();
                JsonHandler.createJsonFromCalendar(calendar, "lastUpdate.json", context);

                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, "DeviceList.json", context);
            }

            @Override
            public void onFailure(Call<ArrayList<Device>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void getReservationList(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Reservation>> call = getPostConnection.getReservation();

        call.enqueue(new Callback<ArrayList<Reservation>>() {
            @Override
            public void onResponse(Call<ArrayList<Reservation>> call,
                                   retrofit2.Response<ArrayList<Reservation>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Reservation> posts = response.body();
                JsonHandler.createJsonFromCalendarList(posts, "ReservationList.json", context);
            }

            @Override
            public void onFailure(Call<ArrayList<Reservation>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void getDeviceOldVersion(int inventoryNumber, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
    Call<ArrayList<Device>> call = getPostConnection.getDeviceOldVersion(inventoryNumber);

    call.enqueue(new Callback<ArrayList<Device>>() {
        @Override
        public void onResponse(Call call, retrofit2.Response response) {
            if (!response.isSuccessful()) {
                Toast.makeText(context,"Keine Verbindung zum Server.",Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            JsonHandler.createJsonFromDeviceList((ArrayList<Device>) response.body(),
                    "DeviceOldVersion.json",
                    context);
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            onFailureMessage(context, t);
        }
    });
    }

    public void postNewReservation(Reservation reservation, final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.postNewReservation(reservation);

        call.enqueue(new Callback<ArrayList<Errors>>() {
            @Override
            public void onResponse(Call<ArrayList<Errors>> call,
                                   retrofit2.Response<ArrayList<Errors>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Errors> errors = (ArrayList<Errors>) response.body();
                showErrors(errors, context);
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Errors>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void getAllUsers(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<UserInfo>> call = getPostConnection.getAllUsers();

        call.enqueue(new Callback<ArrayList<UserInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<UserInfo>> call,
                                   retrofit2.Response<ArrayList<UserInfo>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<UserInfo> posts = response.body();
                JsonHandler.createJsonFromUserInfoList(posts, "UserList.json", context);
            }

            @Override
            public void onFailure(Call<ArrayList<UserInfo>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void postNewDevice(Device device, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.postDevice(device);

        call.enqueue(new Callback<ArrayList<Errors>>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Errors> errors = (ArrayList<Errors>) response.body();
                showErrors(errors, context);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void postNewUser(UserInfo userInfo, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.postUser(userInfo);

        call.enqueue(new Callback<ArrayList<Errors>>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Errors> errors = (ArrayList<Errors>) response.body();
                showErrors(errors, context);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void putChangeDevice(Device device, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.putChangedDevice
                (device.getInventoryNumberInt(), device);

        call.enqueue(new Callback<ArrayList<Errors>>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Errors> errors = (ArrayList<Errors>) response.body();
                showErrors(errors, context);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void putUpdateUser(UserInfo user, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.putUpdateUser(user.getWorkerId(), user);

        call.enqueue(new Callback<ArrayList<Errors>>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Errors> errors = (ArrayList<Errors>) response.body();
                showErrors(errors, context);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void deleteDevice(Device device, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.deleteDevice(device.getInventoryNumberInt(),
                device);

        call.enqueue(new Callback<ArrayList<Errors>>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Errors> errors = (ArrayList<Errors>) response.body();
                showErrors(errors, context);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void deleteUser(UserInfo user, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.deleteUser(user.getWorkerId(), user);

        call.enqueue(new Callback<ArrayList<Errors>>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Errors> errors = (ArrayList<Errors>) response.body();
                showErrors(errors, context);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void deleteReservation(Device device, Reservation reservation, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.deleteReservation(device.getInventoryNumberInt(), reservation);

        call.enqueue(new Callback<ArrayList<Errors>>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Errors> errors = (ArrayList<Errors>) response.body();
                showErrors(errors, context);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public static void getDeviceList(String url, final Context context) {
        RequestQueue mQueue = Volley.newRequestQueue(context);
        final ArrayList<Device> list = new ArrayList<>();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("device");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonDevice = jsonArray.getJSONObject(i);
                                Device device = new Device();

                                device.setInventoryNumber(jsonDevice.getString
                                        ("inventoryNumber"));
                                device.setManufacturer(jsonDevice.getString("manufacturer"));
                                device.setModel(jsonDevice.getString("model"));
                                device.setStatus(jsonDevice.getString("status"));
                                device.setCategory(jsonDevice.getString
                                        ("deviceCategorie"));
                                list.add(device);
                            }
                            JsonHandler.createJsonFromDeviceList(list, "DeviceList.json",
                                    context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue = Volley.newRequestQueue(context);
        mQueue.add(request);
    }

    public static boolean isConnectedToServer(String url, int timeout) {
        try{
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            // Handle your exceptions
            return false;
        }
    }

    public void showErrors(ArrayList<Errors> errors, Context context){
        String message = "";
        for(Errors e: errors){
            message+= e.getMsg() + " ";
        }
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public void getTuev(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Device>> call = getPostConnection.getTuev();

        call.enqueue(new Callback<ArrayList<Device>>() {
            @Override
            public void onResponse(Call<ArrayList<Device>> call,
                                   retrofit2.Response<ArrayList<Device>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, "TuevList.json", context);

            }

            @Override
            public void onFailure(Call<ArrayList<Device>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void getUvv(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Device>> call = getPostConnection.getUvv();

        call.enqueue(new Callback<ArrayList<Device>>() {
            @Override
            public void onResponse(Call<ArrayList<Device>> call,
                                   retrofit2.Response<ArrayList<Device>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, "UvvList.json", context);

            }

            @Override
            public void onFailure(Call<ArrayList<Device>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void getMaintenance(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Device>> call = getPostConnection.getUvv();

        call.enqueue(new Callback<ArrayList<Device>>() {
            @Override
            public void onResponse(Call<ArrayList<Device>> call,
                                   retrofit2.Response<ArrayList<Device>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, "MaintenanceList.json", context);

            }

            @Override
            public void onFailure(Call<ArrayList<Device>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void getBooking(final Context context, UserInfo userInfo){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Device>> call = getPostConnection.getBooking(userInfo.getWorkerId());

        call.enqueue(new Callback<ArrayList<Device>>() {
            @Override
            public void onResponse(Call<ArrayList<Device>> call,
                                   retrofit2.Response<ArrayList<Device>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, "BookingList.json", context);

            }

            @Override
            public void onFailure(Call<ArrayList<Device>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    private static void onFailureMessage(Context context, Throwable t){
        if (t instanceof IOException) {
            Toast.makeText(context, "Keine Verbindung zum Server \uD83D\uDE33", Toast.LENGTH_SHORT).show();
            // logging probably not necessary
        }
        else {
            Toast.makeText(context, "Conversion Error \uD83D\uDE33", Toast.LENGTH_SHORT).show();
            // todo log to some central bug tracking service
        }
    }
}
