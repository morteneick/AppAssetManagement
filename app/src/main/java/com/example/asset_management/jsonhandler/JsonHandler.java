package com.example.asset_management.jsonhandler;

import android.content.Context;

import com.example.asset_management.connection.Errors;
import com.example.asset_management.deviceCard.ui.reservation.Reservation;
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

    public static Boolean createJsonFromLogin(ArrayList<UserInfo> list, String path, Context context){
        String json = convertIntoString(list);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return true;
        } catch (Exception E){
            return false;
        }
    }

    /**
     * Creates Json from Object
     * @param object
     * @param path String with path for the json
     * @param context activity context
     * @return
     */
    public static Boolean createJsonFromObject(Object object, String path, Context context){
        String json = convertIntoString(object);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return true;
        } catch (Exception E){
            return false;
        }
    }

    /**
     * Creates Json from ArrayList<Device>
     * @param list ArrayList with Devices
     * @param path String with path for the json
     * @param context
     * @return
     */
    public static Boolean createJsonFromDeviceList(ArrayList<Device> list, String path, Context context){
        String json = convertIntoString(list);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return true;
        } catch (Exception E){
            return false;
        }
    }

    /**
     * Creates json from single device object
     * @param device device object
     * @param path String with path for the json
     * @param context activity context
     * @return
     */
    public static Boolean createJsonFromDevice(Device device, String path, Context context){
        String json = convertIntoString(device);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return true;
        } catch (Exception E){
            return false;
        }
    }

    /**
     * Creates json from ArrayList Integer
     * @param list Arraylist with Integers
     * @param path String with path for the json
     * @param context activity context
     * @return
     */
    public static Boolean createJsonFromInteger(ArrayList<Integer> list, String path, Context context){
        String json = convertIntoString(list);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return true;
        } catch (Exception E){
            return false;
        }
    }

    /**
     * creates json from ArrayList<Calendar>
     * @param list ArrayList with Calendar objects
     * @param path String with path for the json
     * @param context activity context
     * @return
     */
    public static Boolean createJsonFromCalendarList(ArrayList<Reservation> list, String path, Context context){
        String json = convertIntoString(list);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return true;
        } catch (Exception E){
            return false;
        }
    }

    /**
     * Creates json from single Calendar object
     * @param calendar
     * @param path String with path for the json
     * @param context activity context
     * @return
     */
    public static Boolean createJsonFromCalendar(Calendar calendar, String path, Context context){
        String json = convertIntoString(calendar);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return true;
        } catch (Exception E){
            return false;
        }
    }

    /**
     * creates json from single error object
     * @param errors
     * @param path String with path for the json
     * @param context activity context
     * @return
     */
    public static Boolean createJsonFromErrors(Errors errors, String path, Context context){
        String json = convertIntoString(errors);

        try {
            FileOutputStream fOut = context.openFileOutput(path, Context.MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return true;
        } catch (Exception E){
            return false;
        }
    }

    /**
     * creates json from ArrayList<UserInfo>
     * @param userInfos
     * @param path String with path for the json
     * @param context activity context
     * @return
     */
    public static Boolean createJsonFromUserInfoList(ArrayList<UserInfo> userInfos, String path, Context context){
        String json = convertIntoString(userInfos);

        try {
            FileOutputStream fOut = context.openFileOutput(path, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return true;
        } catch (Exception E){
            return false;
        }
    }

    /**
     * gets a json file as a string
     * @param context activity context
     * @param fileName file path of the json
     * @return string with json object as String
     * @throws IOException
     */
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

    /**
     * gets ArrayList<Device> from a specific json
     * @param filename file path of the json
     * @param context activity context
     * @return ArrayList<Device>
     * @throws IOException
     */
    public static ArrayList<Device> getDeviceList(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        ArrayList<Device> list = gson.fromJson(jsonString,
                new TypeToken<ArrayList<Device>>() {}.getType());

        return list;
    };

    /**
     * Gets ArrayList<UserInfo> from specific json file
     * @param filename file path of the json
     * @param context activity context
     * @return ArrayList with UserInfo objects
     * @throws IOException
     */
    public static ArrayList<UserInfo> getUserList(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        ArrayList<UserInfo> list = gson.fromJson(jsonString,
                new TypeToken<ArrayList<UserInfo>>() {}.getType());

        return list;
    };

    /**
     * Gets Device from json file
     * @param filename file path of the json
     * @param context activity context
     * @return device object
     * @throws IOException
     */
    public static Device getDevice(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        Device device = gson.fromJson(jsonString, Device.class);

        return device;
    };

    /**
     * gets a UserInfo object from json file
     * @param filename file path of the json
     * @param context activity context
     * @return single UserInfo object
     * @throws IOException
     */
    public static UserInfo getUser(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        UserInfo user = gson.fromJson(jsonString, UserInfo.class);

        return user;
    };

    /**
     * gets ArrayList with Integer objects from json file
     * @param filename file path of the json
     * @param context activity context
     * @return ArrayList with integer objects
     * @throws IOException
     */
    public static ArrayList<Integer> getIntegerList(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        ArrayList<Integer> list = gson.fromJson(jsonString,
                new TypeToken<ArrayList<Integer>>() {}.getType());

        return list;
    };

    /**
     * gets ArrayList with Reservations objects from json
     * @param filename file path of the json
     * @param context activity context
     * @return ArrayList<Reservation>
     * @throws IOException
     */
    public static ArrayList<Reservation> getReservationList(String filename, Context context)
            throws IOException {

        String jsonString = getListString(context, filename);

        Gson gson = new Gson();
        ArrayList<Reservation> list = gson.fromJson(jsonString,
                new TypeToken<ArrayList<Reservation>>() {}.getType());

        return list;
    };

    /**
     * Gets calendar object from json file
     * @param filename file path of the json
     * @param context activity context
     * @return single calendar object
     * @throws IOException
     */
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

    /**
     * Clears a json file
     * @param path file path of the json
     * @throws FileNotFoundException
     */
    public static void clearJson(String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path);
        pw.close();
    };

}
