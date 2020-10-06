package com.example.asset_management.connection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asset_management.R;
import com.example.asset_management.deviceCard.ui.reservation.Reservation;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.login.Login;
import com.example.asset_management.login.LoginActivity;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.mainHub.MainHubActivity;
import com.example.asset_management.recycleViewDeviceList.Device;
import com.example.asset_management.settings.SettingsActivity;
import com.google.gson.JsonObject;

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
 * AUTHOR: Dominik Dziersan & Morten Eickmann
 */
public class Connection {

    private String baseURL = "http://10.0.2.2:3000/";
    private String msgNoConnectionServer = "Keine Verbindung zum Server.";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void postLogin(Login login, final Context context, final Activity activity) {
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<JsonObject> call = getPostConnection.postLogin(login);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,"msgNoConnectionServer",Toast.LENGTH_SHORT).show();
                    return;
                }
                JsonObject jsonObject = response.body();
                JsonObject rights = jsonObject.getAsJsonObject("rights");
                UserInfo userInfo = new UserInfo();
                boolean access = jsonObject.get("access").getAsBoolean();
                if (access){

                    JsonHandler.createJsonFromObject(jsonObject, "Login.json", context);

                    userInfo.setWorkerId(jsonObject.get("worker_id").toString());
                    userInfo.seteMail(jsonObject.get("e_mail").toString());
                    userInfo.setFirstname(jsonObject.get("firstName").toString());
                    userInfo.setSurname(jsonObject.get("surname").toString());
                    userInfo.setRole(jsonObject.get("role").toString());

                    userInfo.setDeleteDevice(rights.get("delete_device").getAsInt());
                    userInfo.setEditDevice(rights.get("edit_device").getAsInt());
                    userInfo.setAddDevice(rights.get("add_device").getAsInt());

                    userInfo.setAddUser(rights.get("add_user").getAsInt());
                    userInfo.setDeleteUser(rights.get("delete_user").getAsInt());
                    userInfo.setEditUser(rights.get("edit_user").getAsInt());

                    userInfo.setBookingDevice(rights.get("booking_device").getAsInt());
                    userInfo.setDeleteBooking(rights.get("delete_booking").getAsInt());
                    userInfo.setEditBooking(rights.get("edit_booking").getAsInt());

                    Toast.makeText(context,"Willkommen " +userInfo.getFirstname() + " "
                            + userInfo.getSurname(),Toast.LENGTH_LONG).show();
                    ArrayList<UserInfo> userList = new ArrayList<>();
                    userList.add(userInfo);
                    JsonHandler.createJsonFromUserInfoList(userList, "Login1.json", context);

//                    Toast.makeText(context,"Rights? "+userInfo.getRole()+ userInfo.getBookingDevice()+userInfo.getEditBooking()+userInfo.getEditDevice()+userInfo.getAddDevice()+userInfo.getDeleteUser(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity, MainHubActivity.class);
                    activity.finish();

                    intent.putExtra(context.getString(R.string.user), userInfo);
                    activity.startActivity(intent);

                }
                if (!access)
                {
                    Toast.makeText(context,"Falsche Benutzerdaten.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
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
                String lastUpdateNameJson = context.getString(R.string.lastUpdateNameJSON);
                String deviceListNameJson = context.getString(R.string.deviceListNameJSON);
                Calendar calendar = Calendar.getInstance();
                JsonHandler.createJsonFromCalendar(calendar, lastUpdateNameJson, context);

                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, deviceListNameJson, context);
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
                String reservationListNameJson = context.getString(R.string.reservationListNameJSON);
                ArrayList<Reservation> posts = response.body();
                JsonHandler.createJsonFromCalendarList(posts, reservationListNameJson, context);
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
                Toast.makeText(context,context.getString(
                        R.string.noConnectionServerMessage),Toast.LENGTH_SHORT).show();
                return;
            }
            String deviceListOldVersionNameJson = context.getString
                    (R.string.deviceOldVersionNameJSON);
            JsonHandler.createJsonFromDeviceList((ArrayList<Device>) response.body(),
                    context.getString(R.string.deviceOldVersionNameJSON), context);

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
                JsonHandler.createJsonFromUserInfoList(posts,
                        context.getString(R.string.userListNameJSON), context);
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
        Call<ArrayList<Errors>> call = getPostConnection.deleteReservation(
                device.getInventoryNumberInt(), reservation);

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

//    public static void getDeviceList(String url, final Context context) {
//        RequestQueue mQueue = Volley.newRequestQueue(context);
//        final ArrayList<Device> list = new ArrayList<>();
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("device");
//                            for (int i = 0; i < jsonArray.length(); i++) {
//
//                                JSONObject jsonDevice = jsonArray.getJSONObject(i);
//                                Device device = new Device();
//
//                                device.setInventoryNumber(jsonDevice.getString
//                                        ("inventoryNumber"));
//                                device.setManufacturer(jsonDevice.getString("manufacturer"));
//                                device.setModel(jsonDevice.getString("model"));
//                                device.setStatus(jsonDevice.getString("status"));
//                                device.setCategory(jsonDevice.getString
//                                        ("deviceCategorie"));
//                                list.add(device);
//                            }
//                            JsonHandler.createJsonFromDeviceList(list, "DeviceList.json",
//                                    context);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        mQueue = Volley.newRequestQueue(context);
//        mQueue.add(request);
//    }

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
                String tuevListNameJson = context.getString(R.string.tuevNameJSON);
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, tuevListNameJson, context);

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
                String uvvListNameJson = context.getString(R.string.uvvNameJSON);
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, uvvListNameJson, context);
            }

            @Override
            public void onFailure(Call<ArrayList<Device>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    public void getMaintenance(final Context context){
        GetPostConnection getPostConnection = retrofit.create(GetPostConnection.class);
        Call<ArrayList<Device>> call = getPostConnection.getMaintenance();

        call.enqueue(new Callback<ArrayList<Device>>() {
            @Override
            public void onResponse(Call<ArrayList<Device>> call,
                                   retrofit2.Response<ArrayList<Device>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context,msgNoConnectionServer,Toast.LENGTH_SHORT).show();
                    return;
                }
                String maintenanceListNameJson = context.getString(R.string.maintenanceNameJSON);
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, maintenanceListNameJson, context);
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
                String bookingListNameJson = context.getString(R.string.bookingNameJSON);
                ArrayList<Device> posts = response.body();
                JsonHandler.createJsonFromDeviceList(posts, bookingListNameJson, context);
            }

            @Override
            public void onFailure(Call<ArrayList<Device>> call, Throwable t) {
                onFailureMessage(context, t);
            }
        });
    }

    private static void onFailureMessage(Context context, Throwable t){
        if (t instanceof IOException) {
            Toast.makeText(context, context.getString(R.string.noConnectionServerMessage) +"\uD83D\uDE33",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, context.getString(R.string.conversionErrorMessage),
                    Toast.LENGTH_SHORT)
                    .show();
            Calendar calendar = Calendar.getInstance();
            Errors errors = new Errors();
            errors.setValue(calendar.getTime().toString());
            errors.setBody(t.toString());
            errors.setMsg(context.getString(R.string.conversionErrorMessage));

            String connectionLogJsonName = context.getString(R.string.logsNameJSON);
            JsonHandler.createJsonFromErrorsList(errors, connectionLogJsonName,
                    context);
        }
    }
}
