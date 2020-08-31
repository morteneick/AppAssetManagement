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
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleView.Device;
import com.example.asset_management.recycleView.RecycleActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private String baseURL = "http://localhost:3000/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);

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

    public void createDeviceList(final Context context){
        Call<List<Device>> call = getPostConnection.getDevices();

        call.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, retrofit2.Response<List<Device>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                ArrayList<Device> posts = (ArrayList<Device>) response.body();
                JsonHandler.createJsonFromDeviceList(posts, "DeviceList.json", context);
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {

            }
        });
    }

    public
    void getDeviceHistory(Device device) {
    Call<List<Device>> call = getPostConnection.getDeviceHistory(device);

    call.enqueue(new Callback<List<Device>>() {
        @Override
        public void onResponse(Call call, retrofit2.Response response) {
            if (!response.isSuccessful()) {

                return;
            }

        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    });
    }

    public void postNewDevice(Device device) {
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

}
