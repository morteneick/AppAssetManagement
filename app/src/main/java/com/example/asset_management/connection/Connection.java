package com.example.asset_management.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asset_management.deviceCard.ui.reservation.Reservation;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleView.Device;
import com.example.asset_management.recycleView.RecycleActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static androidx.core.content.ContextCompat.getSystemService;
/**
 * Connection
 * <p>
 *     Version 1.0
 * </p>
 * 30.08.2020
 * AUTHOR: Dominik Dziersan
 */
public class Connection {

    private String baseURL = "http://10.0.2.2:3001/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    public void get2(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<test> call = getPostConnection.get2();

        call.enqueue(new Callback<test>() {
            @Override
            public void onResponse(Call<test> call, retrofit2.Response<test> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show();
                    return;
                }
                test posts = response.body();
                Toast.makeText(context,posts.getTest(),Toast.LENGTH_SHORT).show();
                JsonHandler.createJsonFromObject(posts,"Test.json", context);
            }

            @Override
            public void onFailure(Call<test> call, Throwable t) {
            }
        });
    }

    public void createDeviceList(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Device>> call = getPostConnection.getDevices();

        call.enqueue(new Callback<ArrayList<Device>>() {
            @Override
            public void onResponse(Call<ArrayList<Device>> call,
                                   retrofit2.Response<ArrayList<Device>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,"test",Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, "DeviceList.json", context);
            }

            @Override
            public void onFailure(Call<ArrayList<Device>> call, Throwable t) {

            }
        });
    }

    public void createReservationList(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Reservation>> call = getPostConnection.getReservation();

        call.enqueue(new Callback<ArrayList<Reservation>>() {
            @Override
            public void onResponse(Call<ArrayList<Reservation>> call,
                                   retrofit2.Response<ArrayList<Reservation>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,"Keine Verbindung zum Server.",Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Reservation> posts = response.body();
                JsonHandler.createJsonFromCalendarList(posts, "ReservationList.json", context);
            }

            @Override
            public void onFailure(Call<ArrayList<Reservation>> call, Throwable t) {

            }
        });
    }

    public void getDeviceOldVersion(int inventoryNumber, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
    Call<Device> call = getPostConnection.getDeviceOldVersion(inventoryNumber);

    call.enqueue(new Callback<Device>() {
        @Override
        public void onResponse(Call call, retrofit2.Response response) {
            if (!response.isSuccessful()) {
                Toast.makeText(context,"Keine Verbindung zum Server.",Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            //TODO Old version != deviceCard

            JsonHandler.createJsonFromObject(response.body(), "DeviceOldVersion.json",
                    context);

        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    });
    }

    public void postNewReservation(Reservation reservation, final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<String> call = getPostConnection.postNewReservation(reservation);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,"Keine Verbindung zum Server.",Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                Toast.makeText(context,response.body(),Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void postNewDevice(Device device) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<String> call = getPostConnection.postDevice(device);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {

                    return;
                }
            String responseText = (String) response.body();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void putChangeDevice(Device device) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<String> call = getPostConnection.putChangedDevice(device.getInventoryNumberInt(), device);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {

                    return;
                }
                String responseText = (String) response.body();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void deleteDevice(Device device) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<String> call = getPostConnection.deleteDevice(device.getInventoryNumberInt());

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {

                    return;
                }
                String responseText = (String) response.body();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

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
}
