package com.example.asset_management.jsonhandler;

import android.content.Context;

import com.example.asset_management.connection.Errors;
import com.example.asset_management.deviceCard.ui.reservation.Reservation;
import com.example.asset_management.login.Login;
import com.example.asset_management.login.UserInfo;
import com.example.asset_management.recycleViewDeviceList.Device;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * JsonHandler
 * <p>
 *     Version 1.0
 * </p>
 * 02.06.2020
 */
public class JsonHandler {

    /**
     *  Creates an json String from an object
     * @param object
     * @return json String
     */
    public static String  convertIntoString(Object object){

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        String json = gson.toJson(object);

        return json;
    }

    /**
     * Creates an file in the path from an device object in a path
     * @param object
     * @param path
     * @param context
     * @return
     */

    public static String createJsonFromLogin(ArrayList<UserInfo> list, String path, Context context){
        String json = convertIntoString(list);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return "Success";
        } catch (Exception E){
            return "Failed";
        }
    }

    public static String createJsonFromObject(Object object, String path, Context context){
        String json = convertIntoString(object);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return "Success";
        } catch (Exception E){
            return "Failed";
        }
    }

    public static String createJsonFromDeviceList(ArrayList<Device> list, String path, Context context){
        String json = convertIntoString(list);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return "Success";
        } catch (Exception E){
            return "Failed";
        }
    }

    public static String createJsonFromDevice(Device device, String path, Context context){
        String json = convertIntoString(device);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return "Success";
        } catch (Exception E){
            return "Failed";
        }
    }

    public static String createJsonFromInteger(ArrayList<Integer> list, String path, Context context){
        String json = convertIntoString(list);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return "Success";
        } catch (Exception E){
            return "Failed";
        }
    }

    public static String createJsonFromCalendarList(ArrayList<Reservation> list, String path, Context context){
        String json = convertIntoString(list);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return "Success";
        } catch (Exception E){
            return "Failed";
        }
    }

    public static String createJsonFromCalendar(Calendar calendar, String path, Context context){
        String json = convertIntoString(calendar);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return "Success";
        } catch (Exception E){
            return "Failed";
        }
    }

    public static String createJsonFromErrorsList(Errors errors, String path, Context context){
        String json = convertIntoString(errors);

        try {
            FileOutputStream fOut = context.openFileOutput(path, Context.MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return "Success";
        } catch (Exception E){
            return "Failed";
        }
    }

    public static String createJsonFromUserInfoList(ArrayList<UserInfo> userInfos, String path, Context context){
        String json = convertIntoString(userInfos);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return "Success";
        } catch (Exception E){
            return "Failed";
        }
    }


    public static String getListString(Context context, String fileName)
            throws IOException {

        File file = new File(context.getFilesDir(),fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
// This responce will have Json Format String
        String response = stringBuilder.toString();

        return response;
    };

    public static ArrayList<Device> getDeviceList(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        ArrayList<Device> list = gson.fromJson(jsonString,
                new TypeToken<ArrayList<Device>>() {}.getType());

        return list;
    };

    public static ArrayList<UserInfo> getUserList(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        ArrayList<UserInfo> list = gson.fromJson(jsonString,
                new TypeToken<ArrayList<UserInfo>>() {}.getType());

        return list;
    };

    public static Device getDevice(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        Device device = gson.fromJson(jsonString, Device.class);

        return device;
    };

    public static UserInfo getUser(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        UserInfo user = gson.fromJson(jsonString, UserInfo.class);

        return user;
    };

    public static ArrayList<Integer> getIntegerList(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        ArrayList<Integer> list = gson.fromJson(jsonString,
                new TypeToken<ArrayList<Integer>>() {}.getType());

        return list;
    };

    public static ArrayList<Reservation> getReservationList(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        ArrayList<Reservation> list = gson.fromJson(jsonString,
                new TypeToken<ArrayList<Reservation>>() {}.getType());

        return list;
    };

    public static Calendar getCalendar(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        Calendar calendar = gson.fromJson(jsonString, Calendar.class);

        return calendar;
    };

//    public static Calendar getReservation(String filename, Context context)
//            throws IOException {
//
//        String jsonString = getDeviceListString(context, filename);
//
//        Gson gson = new Gson();
//        Calendar calendar = gson.fromJson(jsonString, Calendar.class);
//
//        return calendar;
//    };

    public static void clearJson(String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path);
        pw.close();
    };

}
