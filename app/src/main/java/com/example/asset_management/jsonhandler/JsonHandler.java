package com.example.asset_management.jsonhandler;

import android.content.Context;

import com.example.asset_management.recycleView.Device;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


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
            FileOutputStream fOut = context.openFileOutput(path, MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.append(json);
            osw.flush();
            osw.close();
            return "Success";
        } catch (Exception E){
            return "Failed";
        }
    }


//    public static ArrayList<Device> jsonParse(String url){
//
//      final ArrayList<Device> list = new ArrayList<>();
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
//                                device.setDeviceCategorie(jsonDevice.getString
//                                        ("deviceCategorie"));
//                                list.add(device);
//                            }
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
//
////        mQueue = Volley.newRequestQueue(RecycleActivity.this);
////        mQueue.add(request);
//        return list;
//    }

}
