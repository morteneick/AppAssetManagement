package com.example.asset_management.addDevice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;


import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleViewDeviceList.Device;


/**
 * AddDeviceActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 * AUTHOR: Dominik Dziersan
 * TODO(DZIERSAN) add all device features the customer wants
 */

public class AddDeviceActivity extends AppCompatActivity {
    String jsonName = "NewDevice.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        Toolbar toolbar = findViewById(R.id.toolbardevicecard);
        setSupportActionBar(toolbar);

        Button btnSave = findViewById(R.id.button4);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    Device device = new Device(findViewById(R.id.editInventoryNumber),
                            findViewById(R.id.editSerialNumber),
                            findViewById(R.id.editModel),
                            findViewById(R.id.editManufacturer),
                            findViewById(R.id.editCategorie),
                                    findViewById(R.id.editStatus));

                    String createDeviceMessage = JsonHandler.createJsonFromObject(device,
                            jsonName, getApplicationContext());

                Connection connection = new Connection();
                connection.postNewDevice(device, getApplicationContext());

//                    Toast.makeText(getApplicationContext(), createDeviceMessage, Toast.LENGTH_SHORT)
//                            .show();

            }
        });
    }
}


