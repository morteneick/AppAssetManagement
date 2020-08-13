package com.example.asset_management.addDevice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.asset_management.R;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.recycleView.Device;


/**
 * AddDeviceActivity
 * <p>
 *     Version 1.0
 * </p>
 * 11.05.2020
 * TODO(DZIERSAN) add all device features the customer wants
 */

public class AddDeviceActivity extends AppCompatActivity {
    String jsonName = "Device.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

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
                String test = device.toString();
//                Device device = Device.addDevice(editInventoryNumber, editSerialNumber, editModel, editManufacturer, editCategorie, editStatus);

                    String createDeviceMessage = JsonHandler.createJsonFromObject(device,
                            jsonName, getApplicationContext());

                    Toast.makeText(getApplicationContext(), createDeviceMessage, Toast.LENGTH_SHORT)
                            .show();

            }
        });
    }
}

//    public void addDevice() throws IOException {
//
//        EditText editInventoryNumber = (EditText) findViewById(R.id.editInventoryNumber);
//        EditText editSerialNumber = (EditText) findViewById(R.id.editSerialNumber);
//        EditText editModel = (EditText) findViewById(R.id.editModel);
//        EditText editManufacturer = (EditText) findViewById(R.id.editManufacturer);
//        EditText editCategorie = (EditText) findViewById(R.id.editCategorie);
//        EditText editStatus = (EditText) findViewById(R.id.editStatus);
//
//        String stringInventoryNumber = editInventoryNumber.getText().toString();
//        String stringSerialNumber = editSerialNumber.getText().toString();
//        String stringEditModel = editModel.getText().toString();
//        String stringManufacturer = editManufacturer.getText().toString();
//        String stringCategorie = editCategorie.getText().toString();
//        String stringStatus = editStatus.getText().toString();
//
//        Device device = new Device(stringInventoryNumber, stringEditModel, stringManufacturer,
//                stringCategorie, stringStatus);
//
//
//        String fileCreatedMessage = JsonHandler.createJsonFromDevice(device,jsonName, getApplicationContext());
//
//        Toast.makeText(getApplicationContext(), fileCreatedMessage, Toast.LENGTH_SHORT)
//                .show();
//    }

//                    EditText editInventoryNumber = (EditText) findViewById(R.id.editInventoryNumber);
//                    EditText editSerialNumber = (EditText) findViewById(R.id.editSerialNumber);
//                    EditText editModel = (EditText) findViewById(R.id.editModel);
//                    EditText editManufacturer = (EditText) findViewById(R.id.editManufacturer);
//                    EditText editCategorie = (EditText) findViewById(R.id.editCategorie);
//                    EditText editStatus = (EditText) findViewById(R.id.editStatus);

