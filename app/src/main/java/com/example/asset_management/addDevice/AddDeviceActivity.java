package com.example.asset_management.addDevice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        Button btnSave = findViewById(R.id.btnAddSave);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                    Device device = new Device(findViewById(R.id.editInventoryNumber),
//                            findViewById(R.id.editSerialNumber),
//                            findViewById(R.id.editModel),
//                            findViewById(R.id.editManufacturer),
//                            findViewById(R.id.editCategorie),
//                                    findViewById(R.id.editStatus));
                EditText editInventoryNumber = findViewById(R.id.editAddInventoryNumber);
                EditText editSerialnumber = findViewById(R.id.editAddSerialNumber);
                EditText editModel = findViewById(R.id.editAddModel);
                EditText editManufacturer = findViewById(R.id.editAddManufacturer);
                EditText editCategory = findViewById(R.id.editAddCategory);

                Device device = new Device();
                device.setInventoryNumber(editInventoryNumber.getText().toString());
                device.setSerialNumber(editSerialnumber.getText().toString());
                device.setModel(editModel.getText().toString());
                device.setManufacturer(editManufacturer.getText().toString());
                device.setCategory(editCategory.getText().toString());
                device.setDeviceStatus(1);

                Connection connection = new Connection();
                connection.postNewDevice(device, getApplicationContext());


            }
        });
    }
}


