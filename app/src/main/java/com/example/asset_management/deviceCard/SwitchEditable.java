package com.example.asset_management.deviceCard;

import android.content.Context;

import com.example.asset_management.connection.Connection;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.google.gson.Gson;

import java.io.IOException;

public class SwitchEditable {

    private boolean isEnabled;

    public SwitchEditable(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }


    public static void createSwitch(Object object, Context context){
        JsonHandler.createJsonFromObject(object,"Switch.json",context);
    }

    public static String getSwitch(Context context) throws IOException {
        return JsonHandler.getListString(context, "Switch.json");
    }

    public static boolean isClicked(Context context){
        String isEditable = "";
        try {
            isEditable = SwitchEditable.getSwitch(context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        final SwitchEditable switchEditable = gson.fromJson(isEditable, SwitchEditable.class);
        return switchEditable.isEnabled();
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
