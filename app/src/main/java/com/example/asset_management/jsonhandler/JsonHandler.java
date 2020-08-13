package com.example.asset_management.jsonhandler;

import android.content.Context;

import com.example.asset_management.recycleView.Device;

import com.google.gson.Gson;
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


import static android.content.Context.MODE_PRIVATE;
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
        Gson gson = new Gson();
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
    public static String createJsonFromDevice(Object object, String path, Context context){
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

    /**
     *
     * @param list
     * @param path
     * @param context
     * @return
     */
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

    public static String getDeviceListString(Context context, String fileName)
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

    public static ArrayList<Device> getDeviceList(Context context, String fileName)
            throws IOException {

        String jsonString = getDeviceListString(context, fileName);

        Gson gson = new Gson();
        ArrayList<Device> list = gson.fromJson(jsonString,
                new TypeToken<ArrayList<Device>>() {}.getType());

        return list;
    };

    public static void clearJson(String path) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(path);
        pw.close();
    };

}
