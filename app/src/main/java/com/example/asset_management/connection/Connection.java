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

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    private String baseURL = "http://10.0.2.2:3000/";
    private String msgNoConnectionServer = "Keine Verbindung zum Server.";
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void get(final Context context){
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<Test> call = getPostConnection.getTest();

        call.enqueue(new Callback<Test>() {
            @Override
            public void onResponse(Call<Test> call,
                                   retrofit2.Response<Test> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                Test posts = response.body();
                JsonHandler.createJsonFromObject(posts, "Test.json", context);
            }

            @Override
            public void onFailure(Call<Test> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(context, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(context, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }

    public void get1(final Context context){
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Device>> call = getPostConnection.getDevices1();

        call.enqueue(new Callback<ArrayList<Device>>() {
            @Override
            public void onResponse(Call<ArrayList<Device>> call,
                                   retrofit2.Response<ArrayList<Device>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromObject(posts, "Test.json", context);
            }

            @Override
            public void onFailure(Call<ArrayList<Device>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(context, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(context, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }

    public void getLoginData(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<UserInfo>> call = getPostConnection.getLogin();

        call.enqueue(new Callback<ArrayList<UserInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<UserInfo>> call,
                                   retrofit2.Response<ArrayList<UserInfo>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<UserInfo> posts = response.body();
                JsonHandler.createJsonFromLogin(posts, "Login.json", context);
            }

            @Override
            public void onFailure(Call<ArrayList<UserInfo>> call, Throwable t) {
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postLogin(Login login, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.postLogin(login);

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
                Toast.makeText(context,call.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * GETs a list with all devices and saves them into an internal json file.
     * @param context activity context
     */
    public void getDeviceList(final Context context){
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

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
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, "DeviceList.json", context);

                Calendar calendar = Calendar.getInstance();
                JsonHandler.createJsonFromCalendar(calendar, "lastUpdate.json", context);
            }

            @Override
            public void onFailure(Call<ArrayList<Device>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(context, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(context, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }
            }
        });
    }

    /**
     * GETs a list of all reseravtions for all devices and saves them into an internal json file.
     * @param context activity context
     */
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

            }
        });
    }

    /**
     * GETs all old versions from one specific device and saves them into an json file
     * @param inventoryNumber number from the device
     * @param context activity context
     */
    public void getDeviceOldVersion(int inventoryNumber, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
    Call<ArrayList<Device>> call = getPostConnection.getDeviceOldVersion(inventoryNumber);

    call.enqueue(new Callback<ArrayList<Device>>() {
        @Override
        public void onResponse(Call call, retrofit2.Response response) {
            if (!response.isSuccessful()) {
                Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            JsonHandler.createJsonFromDeviceList((ArrayList<Device>) response.body(), "DeviceOldVersion.json",
                    context);
        }

        @Override
        public void onFailure(Call call, Throwable t) {

        }
    });
    }

    /**
     * GETs only one user with the givin workerId
     * @param workerId Id of the person you want to get all informations
     * @param context activity context
     */
    public void getUser(int workerId, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<UserInfo> call = getPostConnection.getUser(workerId);

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                JsonHandler.createJsonFromObject(response.body(), "User.json", context);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    /**
     * POSTs the reservation to the server and receives and ErrorMessage if it failed.
     * @param reservation new reservation object
     * @param context activity context
     */
    public void postNewReservation(Reservation reservation, final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.postNewReservation(reservation);

        call.enqueue(new Callback<ArrayList<Errors>>() {
            @Override
            public void onResponse(Call<ArrayList<Errors>> call, retrofit2.Response<ArrayList<Errors>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Errors> errors = (ArrayList<Errors>) response.body();
                showErrors(errors, context);
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<Errors>> call, Throwable t) {

            }
        });
    }

    /**
     * GETs all users and saves them into a json file.
     * @param context activity context
     */
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
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     *  POSTs new device to the server and receives and ErrorMessage if it failed.
     * @param device new device object
     * @param context activitiy context.
     */
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
                Toast.makeText(context,call.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * PUTs the device to the server with an edited device object
     * @param device changed/edited device object
     * @param context activity context
     */
    public void putChangeDevice(Device device, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.putChangedDevice(device.getInventoryNumberInt(), device);

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

            }
        });
    }

    /**
     * PUTs an edited user to the server
     * @param user edited user object
     * @param context activity context
     */
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

            }
        });
    }

    /**
     * DELETE request to the server with the devices inventory number
     * @param device device object
     * @param context activity context
     */
    public void deleteDevice(Device device, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.deleteDevice(device.getInventoryNumberInt());

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

            }
        });
    }

    /**
     * DELETE request with the users workerId
     * @param user user object
     * @param context activity context
     */
    public void deleteUser(UserInfo user, final Context context) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Errors>> call = getPostConnection.deleteDevice(user.getWorkerId());

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

            }
        });
    }

    /**
     * DELETE request fpr a reservation from one device
     * @param device device object with the reservation
     * @param reservation reservation object
     * @param context activity context
     */
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

            }
        });
    }

    /**
     *  GETs all devices from the server ans saves them into a json file
     * @param url http
     * @param context activity context
     * @deprecated
     */
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

    /**
     * Tests the connection to the given url
     * @param url
     * @param timeout int in milliseconds for how long to wait until it fails
     * @return true or false if connection works
     */
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
}
